package com.analise.credito.service.strategy;

import com.analise.credito.domain.Proposta;
import org.springframework.stereotype.Component;

@Component
public class PrazoPagamentoMenorQueDezAnosImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }
}
