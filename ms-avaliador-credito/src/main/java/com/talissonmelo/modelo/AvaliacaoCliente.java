package com.talissonmelo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AvaliacaoCliente {
    private List<CartaoAprovado> cartoesAprovados;
}
