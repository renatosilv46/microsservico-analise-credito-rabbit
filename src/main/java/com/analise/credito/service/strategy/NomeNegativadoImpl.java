package com.analise.credito.service.strategy;

import com.analise.credito.constant.MensagensConstant;
import com.analise.credito.domain.Proposta;
import com.analise.credito.exception.StrategyException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
       if(nomeNegativado()) {
           throw new StrategyException(String.format(MensagensConstant.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
       }
       return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }
}
