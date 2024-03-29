package com.talissonmelo.aplicacao;

import com.talissonmelo.exception.ComunicacaoMicroservicoException;
import com.talissonmelo.exception.DadosClientesNaoEncontrados;
import com.talissonmelo.exception.SolicitacaoCartaoException;
import com.talissonmelo.modelo.*;
import com.talissonmelo.servico.AvaliadorCreditoServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity buscarSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoServico.buscarSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClientesNaoEncontrados e) {
            return ResponseEntity.notFound().build();
        } catch (ComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            AvaliacaoCliente avaliacaoCliente = avaliadorCreditoServico.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return  ResponseEntity.ok(avaliacaoCliente);
        } catch (DadosClientesNaoEncontrados e) {
            return ResponseEntity.notFound().build();
        } catch (ComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping(value = "/solicitacao-cartao")
    public ResponseEntity solicitacaoCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
        try {
            ProtocoloSolicitacaoCartao solicitacaoCartao = avaliadorCreditoServico.solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(solicitacaoCartao);
        } catch (SolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
