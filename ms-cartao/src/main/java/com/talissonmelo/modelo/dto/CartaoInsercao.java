package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoInsercao {

    private String nome;
    private BigDecimal renda;
    private BigDecimal limite;
    private BandeiraCartao bandeiraCartao;
}
