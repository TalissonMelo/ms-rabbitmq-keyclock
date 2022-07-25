package com.talissonmelo.aplicacao;

import com.talissonmelo.modelo.Cartao;
import com.talissonmelo.modelo.dto.CartaoInsercao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cartoes")
@RequiredArgsConstructor
public class CartaoControlador {

    private final CartaoServico servico;

    @GetMapping(value = "/status")
    public String status() {
        return "MS_CARTÃO OK!.";
    }

    @PostMapping
    public ResponseEntity salvar(CartaoInsercao insercao){
        Cartao cartao = new Cartao(insercao);
        servico.salvar(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
