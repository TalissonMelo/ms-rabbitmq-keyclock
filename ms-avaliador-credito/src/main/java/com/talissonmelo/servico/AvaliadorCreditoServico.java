package com.talissonmelo.servico;

import com.talissonmelo.infraestrutura.CartaoFeign;
import com.talissonmelo.infraestrutura.ClienteFeign;
import com.talissonmelo.modelo.CartaoCliente;
import com.talissonmelo.modelo.DadosCliente;
import com.talissonmelo.modelo.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServico {

    private final ClienteFeign clienteFeign;
    private final CartaoFeign cartaoFeign;

    public SituacaoCliente buscarSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> cliente = clienteFeign.buscarClientePorCpf(cpf);
        ResponseEntity<List<CartaoCliente>> cartaoClientes = cartaoFeign.getCartaoClienteCpf(cpf);

        return SituacaoCliente.builder()
                .cliente(cliente.getBody())
                .cartoes(cartaoClientes.getBody())
                .build();
    }
}
