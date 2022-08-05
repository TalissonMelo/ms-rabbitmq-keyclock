package com.talissonmelo.infraestrutura;

import com.talissonmelo.modelo.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "ms-cartao",
        //url = "http://localhost:8080",
        path = "/cartoes"
)
public interface CartaoFeign {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartaoClienteCpf(@RequestParam("cpf") String cpf);
}
