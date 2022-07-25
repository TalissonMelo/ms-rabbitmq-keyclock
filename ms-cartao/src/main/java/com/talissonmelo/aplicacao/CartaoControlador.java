package com.talissonmelo.aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoControlador {

    @GetMapping(value = "/status")
    public String status() {
        return "MS_CARTÃO OK!.";
    }
}
