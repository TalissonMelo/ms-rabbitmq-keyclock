package com.talissonmelo.servico;

import com.talissonmelo.exception.ComunicacaoMicroservicoException;
import com.talissonmelo.exception.DadosClientesNaoEncontrados;
import com.talissonmelo.exception.SolicitacaoCartaoException;
import com.talissonmelo.infraestrutura.CartaoFeign;
import com.talissonmelo.infraestrutura.ClienteFeign;
import com.talissonmelo.infraestrutura.rabbitmq.SolicitacaoEmissaoCartaoPublisher;
import com.talissonmelo.modelo.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServico {

    private final ClienteFeign clienteFeign;
    private final CartaoFeign cartaoFeign;

    private final SolicitacaoEmissaoCartaoPublisher publisher;

    public SituacaoCliente buscarSituacaoCliente(String cpf) throws DadosClientesNaoEncontrados, ComunicacaoMicroservicoException {

        try {
            ResponseEntity<DadosCliente> cliente = clienteFeign.buscarClientePorCpf(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoClientes = cartaoFeign.getCartaoClienteCpf(cpf);

            return SituacaoCliente.builder()
                    .cliente(cliente.getBody())
                    .cartoes(cartaoClientes.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new DadosClientesNaoEncontrados();
            }
            throw new ComunicacaoMicroservicoException(e.getMessage(), e.status());
        }

    }

    public AvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClientesNaoEncontrados, ComunicacaoMicroservicoException {
        try {
            ResponseEntity<DadosCliente> dadosClientes = clienteFeign.buscarClientePorCpf(cpf);
            ResponseEntity<List<Cartao>> rendaCartoes = cartaoFeign.getCartoesRendaAte(renda);

            List<Cartao> cartoes = rendaCartoes.getBody();

            List<CartaoAprovado> cartaoAprovados = cartoes.stream().map((cartao) -> {
                DadosCliente dadosCliente = dadosClientes.getBody();
                BigDecimal valor = cartao.getLimite();
                BigDecimal idadeDadosCliente = BigDecimal.valueOf(dadosCliente.getIdade());
                BigDecimal fator = idadeDadosCliente.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(valor);

                CartaoAprovado cartaoAprovado = new CartaoAprovado();
                cartaoAprovado.setCpf(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeiraCartao());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);
                return cartaoAprovado;
            }).collect(Collectors.toList());

            return new AvaliacaoCliente(cartaoAprovados);

        } catch (FeignException.FeignClientException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new DadosClientesNaoEncontrados();
            }
            throw new ComunicacaoMicroservicoException(e.getMessage(), e.status());
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            publisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new SolicitacaoCartaoException(e.getMessage());
        }
    }
}
