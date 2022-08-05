package com.talissonmelo.aplicacao;

import com.talissonmelo.servico.AvaliadorCreditoServico;
import com.talissonmelo.modelo.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoControlador {

    private final AvaliadorCreditoServico avaliadorCreditoServico;

    @GetMapping(value = "/status")
    public String status() {
        return "MS_AVALIADOR_CRÉDITO OK!.";
    }

    @GetMapping(value = "/situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> buscarSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = avaliadorCreditoServico.buscarSituacaoCliente(cpf);
        return ResponseEntity.ok(situacaoCliente);

    }
}