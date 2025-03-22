package com.analise.credito.service.strategy;

import com.analise.credito.domain.Proposta;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimosEmAndamento implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return this.outrosEmprestimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamento() {
        return new Random().nextBoolean();
    }
}
