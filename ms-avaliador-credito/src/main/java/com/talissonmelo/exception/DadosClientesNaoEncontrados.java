package com.talissonmelo.exception;

public class DadosClientesNaoEncontrados extends Exception {

    public DadosClientesNaoEncontrados() {
        super("Dados do cliente não encontrados para o CPF informado!.");
    }
}
