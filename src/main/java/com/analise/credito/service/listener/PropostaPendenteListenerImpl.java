package com.analise.credito.service.listener;

import com.analise.credito.domain.Proposta;
import com.analise.credito.service.analiseCredito.AnaliseCreditoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PropostaPendenteListenerImpl implements PropostaPendenteListener {

    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_PENDENTE_LISTENER =
            "[PropostaPendenteListener]";
    private final AnaliseCreditoService analiseCreditoService;

    public PropostaPendenteListenerImpl(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void recebeMensagemToPropostaPendente(Proposta proposta) {
        log.info("{} Proposta recebida pela queue: ${rabbitmq.queue.proposta.pendente}",
                IDENTIFICADOR_OPERACAO_PROPOSTA_PENDENTE_LISTENER);
        this.analiseCreditoService.analisar(proposta);
    }
}
