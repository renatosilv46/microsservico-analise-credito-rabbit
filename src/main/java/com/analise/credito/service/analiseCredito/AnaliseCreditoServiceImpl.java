package com.analise.credito.service.analiseCredito;

import com.analise.credito.domain.Proposta;
import com.analise.credito.exception.StrategyException;
import com.analise.credito.service.rabbitmq.NotificacaoService;
import com.analise.credito.service.strategy.CalculoPonto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoServiceImpl implements AnaliseCreditoService {

    private final List<CalculoPonto> listaCalculoPonto;
    private final NotificacaoService notificacaoService;
    private final String exchangePropostaConcluida;

    public AnaliseCreditoServiceImpl(
            List<CalculoPonto> listaCalculoPonto,
            NotificacaoService notificacaoService,
            @Value("${rabbitmq-proposta-concluida-exchange}")
            String exchangePropostaConcluida) {
        this.listaCalculoPonto = listaCalculoPonto;
        this.notificacaoService = notificacaoService;
        this.exchangePropostaConcluida = exchangePropostaConcluida;
    }

    @Override
    public void analisar(Proposta proposta) {
        try{
            int pontos  = listaCalculoPonto.stream()
                            .mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos >= 200);
        } catch(StrategyException ex) {
            proposta.setAprovada(false);
            proposta.setObservacao(ex.getMessage());
        }
        this.notificacaoService.notificar(exchangePropostaConcluida, proposta);
    }

}
