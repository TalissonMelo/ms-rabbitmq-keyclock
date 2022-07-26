package com.talissonmelo.aplicacao;

import com.talissonmelo.modelo.Cartao;
import com.talissonmelo.modelo.dto.CartaoInsercao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> cartoes = servico.buscarCartaoRendaMenorIgual(renda);
        return ResponseEntity.ok().body(cartoes);
    }
}
