package com.analise.credito.service.strategy;

import com.analise.credito.domain.Proposta;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorQueValorSolicitadoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return this.rendaMaiorQueValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorQueValorSolicitado(Proposta proposta) {
        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }
}
