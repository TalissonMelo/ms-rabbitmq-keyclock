package com.talissonmelo.exception;

import lombok.Getter;

public class ComunicacaoMicroservicoException extends Exception {

    @Getter
    private Integer status;

    public ComunicacaoMicroservicoException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
