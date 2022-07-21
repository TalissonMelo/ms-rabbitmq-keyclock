package com.talissonmelo.aplicacao;

import com.talissonmelo.Infraestrutura.Repositorio.ClienteRepositorio;
import com.talissonmelo.modelo.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepositorio repositorio;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public Optional<Cliente> getClientePorCfp(String cpf){
        return repositorio.findByCpf(cpf);
    }
}
