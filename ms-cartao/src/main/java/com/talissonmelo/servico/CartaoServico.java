package com.talissonmelo.servico;

import com.talissonmelo.infraestrutura.repositorio.CartaoRepositorio;
import com.talissonmelo.modelo.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoServico {

    private final CartaoRepositorio repositorio;

    @Transactional
    public Cartao salvar(Cartao cartao){
        return repositorio.save(cartao);
    }

    public List<Cartao> buscarCartaoRendaMenorIgual(Long renda) {
        BigDecimal valor = BigDecimal.valueOf(renda);
        return repositorio.findByRendaLessThanEqual(valor);
    }
}
