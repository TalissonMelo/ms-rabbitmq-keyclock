package com.talissonmelo.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private BigDecimal limite;

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
}
