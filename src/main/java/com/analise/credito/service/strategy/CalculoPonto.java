package com.analise.credito.service.strategy;

import com.analise.credito.domain.Proposta;

public interface CalculoPonto {
    int calcular(Proposta proposta);
}
