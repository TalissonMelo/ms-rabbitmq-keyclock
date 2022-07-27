package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartoesPorCliente {
    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public static CartoesPorCliente setCartaoPorCliente(ClienteCartao cartao) {
        return new CartoesPorCliente(cartao.getCartao().getNome(), cartao.getCartao().getBandeiraCartao().toString(), cartao.getLimite());
    }
}
