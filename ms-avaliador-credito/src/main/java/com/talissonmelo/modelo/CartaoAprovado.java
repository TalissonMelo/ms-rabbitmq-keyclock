package com.talissonmelo.modelo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovado {
    private String cpf;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
