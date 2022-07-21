package com.talissonmelo.aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteControlador {

    @GetMapping
    public String status() {
        return "MS_CLIENTE OK!.";
    }
}
