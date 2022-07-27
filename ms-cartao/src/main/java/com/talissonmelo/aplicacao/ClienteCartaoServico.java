package com.talissonmelo.aplicacao;

import com.talissonmelo.infraestrutura.repositorio.ClienteCartaoRepositorio;
import com.talissonmelo.modelo.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoServico {

    private final ClienteCartaoRepositorio repositorio;

    public List<ClienteCartao> listarCartaoPorCpf(String cpf) {
        return repositorio.findByCpf(cpf);
    }
}
