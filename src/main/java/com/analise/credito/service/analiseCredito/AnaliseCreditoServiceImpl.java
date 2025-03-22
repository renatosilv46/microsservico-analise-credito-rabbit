package com.analise.credito.service.analiseCredito;

import com.analise.credito.domain.Proposta;
import com.analise.credito.exception.StrategyException;
import com.analise.credito.service.rabbitmq.NotificacaoService;
import com.analise.credito.service.strategy.CalculoPonto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AnaliseCreditoServiceImpl implements AnaliseCreditoService {

    private static final String IDENTIFICADOR_OPERACAO_ANALISE_CREDITO_SERVICE =
            "[AnaliseCreditoService]";

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
            log.info("{} Iniciando análise da proposta",
                    IDENTIFICADOR_OPERACAO_ANALISE_CREDITO_SERVICE);
            int pontos  = listaCalculoPonto.stream()
                            .mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos >= 200);
        } catch(StrategyException ex) {
            proposta.setAprovada(false);
            proposta.setObservacao(ex.getMessage());
        }
        log.info("{} Proposta analisada com sucesso | Status proposta: {}",
                IDENTIFICADOR_OPERACAO_ANALISE_CREDITO_SERVICE, proposta.getAprovada());
        this.notificacaoService.notificar(exchangePropostaConcluida, proposta);
    }

}
