package com.talissonmelo.infraestrutura;

import com.talissonmelo.modelo.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "ms-cliente",
        //url = "http://localhost:8080",
        path = "/clientes"
)
public interface ClienteFeign {

    @GetMapping
    ResponseEntity<DadosCliente> buscarClientePorCpf(@RequestParam("cpf") String cpf);
}
