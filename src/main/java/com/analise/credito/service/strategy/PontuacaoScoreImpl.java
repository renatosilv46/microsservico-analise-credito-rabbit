package com.analise.credito.service.strategy;

import com.analise.credito.constant.MensagensConstant;
import com.analise.credito.domain.Proposta;
import com.analise.credito.exception.StrategyException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        int score = score();
        if(score < 200) {
            throw new StrategyException(String.format(MensagensConstant.PONTUACAO_SERASA_BAIXA, proposta.getUsuario().getNome()));
        } else if(score <= 400) {
            return 150;
        } else if(score <= 600) {
            return 180;
        } else {
            return 220;
        }
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
