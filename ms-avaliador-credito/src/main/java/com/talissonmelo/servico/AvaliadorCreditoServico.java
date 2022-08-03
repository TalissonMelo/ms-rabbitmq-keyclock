package com.talissonmelo.servico;

import com.talissonmelo.infraestrutura.ClienteFeign;
import com.talissonmelo.modelo.DadosCliente;
import com.talissonmelo.modelo.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServico {

    private final ClienteFeign clienteFeign;

    public SituacaoCliente buscarSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> cliente = clienteFeign.buscarClientePorCpf(cpf);
        //Obter cartões do cliente
        return SituacaoCliente.builder()
                .cliente(cliente.getBody())
                .build();
    }
}
