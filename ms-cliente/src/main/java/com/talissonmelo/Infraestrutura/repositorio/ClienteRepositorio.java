package com.talissonmelo.Infraestrutura.repositorio;

import com.talissonmelo.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

    @Query(nativeQuery = true,
    value = "SELECT cli.* FROM cliente cli WHERE cli.cpf = ':cpf'")
    Optional<Cliente> buscarClienteProCpf(String cpf);
}
