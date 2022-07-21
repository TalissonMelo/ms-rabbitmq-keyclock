package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.Cliente;
import lombok.Data;

@Data
public class ClienteSalvar {

    private String nome;
    private String cfp;
    private Integer idade;

    public Cliente clienteSalvarParaCliente(){
        return new Cliente(this.nome, this.cfp, this.idade);
    }
}
