package com.talissonmelo.modelo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cartao {
    private Long id;
    private String nome;
    private BigDecimal limite;
    private String bandeiraCartao;
}
