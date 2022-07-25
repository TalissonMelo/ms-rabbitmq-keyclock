package com.talissonmelo.modelo;

import com.talissonmelo.modelo.dto.CartaoInsercao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal renda;
    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;

    public Cartao(CartaoInsercao insercao) {
        this.nome = insercao.getNome();
        this.renda = insercao.getRenda();
        this.limite = insercao.getLimite();
        this.bandeiraCartao = insercao.getBandeiraCartao();
    }
}
