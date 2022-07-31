package com.talissonmelo.aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoControlador {

    @GetMapping(value = "/status")
    public String status() {
        return "MS_AVALIADOR_CRÉDITO OK!.";
    }
}
