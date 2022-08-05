package com.talissonmelo.servico;

import com.talissonmelo.exception.ComunicacaoMicroservicoException;
import com.talissonmelo.exception.DadosClientesNaoEncontrados;
import com.talissonmelo.infraestrutura.CartaoFeign;
import com.talissonmelo.infraestrutura.ClienteFeign;
import com.talissonmelo.modelo.CartaoCliente;
import com.talissonmelo.modelo.DadosCliente;
import com.talissonmelo.modelo.SituacaoCliente;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServico {

    private final ClienteFeign clienteFeign;
    private final CartaoFeign cartaoFeign;

    public SituacaoCliente buscarSituacaoCliente(String cpf) throws DadosClientesNaoEncontrados, ComunicacaoMicroservicoException {

        try{
            ResponseEntity<DadosCliente> cliente = clienteFeign.buscarClientePorCpf(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoClientes = cartaoFeign.getCartaoClienteCpf(cpf);

            return SituacaoCliente.builder()
                    .cliente(cliente.getBody())
                    .cartoes(cartaoClientes.getBody())
                    .build();

        }catch (FeignException.FeignClientException e){
            if(HttpStatus.NOT_FOUND.value() == e.status()){
                throw new DadosClientesNaoEncontrados();
            }
            throw  new ComunicacaoMicroservicoException(e.getMessage(), e.status());
        }

    }
}
