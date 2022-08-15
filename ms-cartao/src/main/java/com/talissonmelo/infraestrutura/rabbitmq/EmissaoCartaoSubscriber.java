package com.talissonmelo.infraestrutura.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talissonmelo.infraestrutura.repositorio.CartaoRepositorio;
import com.talissonmelo.infraestrutura.repositorio.ClienteCartaoRepositorio;
import com.talissonmelo.modelo.Cartao;
import com.talissonmelo.modelo.ClienteCartao;
import com.talissonmelo.modelo.DadosSolicitacaoEmissaoCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepositorio cartaoRepositorio;
    private final ClienteCartaoRepositorio clienteCartaoRepositorio;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String msg) {

        try {
            var mapper = new ObjectMapper();

            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepositorio.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteliberado());

            clienteCartaoRepositorio.save(clienteCartao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
