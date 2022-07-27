package com.talissonmelo.aplicacao;

import com.talissonmelo.modelo.Cartao;
import com.talissonmelo.modelo.ClienteCartao;
import com.talissonmelo.modelo.dto.CartaoInsercao;
import com.talissonmelo.modelo.dto.CartoesPorCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cartoes")
@RequiredArgsConstructor
public class CartaoControlador {

    private final CartaoServico cartaoServico;
    private final ClienteCartaoServico clienteCartaoServico;

    @GetMapping(value = "/status")
    public String status() {
        return "MS_CARTÃO OK!.";
    }

    @PostMapping
    public ResponseEntity salvar(CartaoInsercao insercao){
        Cartao cartao = new Cartao(insercao);
        cartaoServico.salvar(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> cartoes = cartaoServico.buscarCartaoRendaMenorIgual(renda);
        return ResponseEntity.ok().body(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorCliente>> getCartaoClienteCpf(@RequestParam("cpf") String cpf){
        List<ClienteCartao> clienteCartoes = clienteCartaoServico.listarCartaoPorCpf(cpf);
        List<CartoesPorCliente> cartoesPorClientes = clienteCartoes.stream()
                        .map(CartoesPorCliente::setCartaoPorCliente)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(cartoesPorClientes);
    }
}
