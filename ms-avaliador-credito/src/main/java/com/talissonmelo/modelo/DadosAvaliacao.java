package com.talissonmelo.modelo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosAvaliacao {
    private String cpf;
    private BigDecimal renda;
}
