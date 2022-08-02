package com.talissonmelo.modelo;

import lombok.Data;

import java.util.List;

@Data
public class SituacaoCliente {

    private DadosCliente client;
    private List<CartaoCliente> cartoes;
}
