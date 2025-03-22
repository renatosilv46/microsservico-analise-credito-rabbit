package com.analise.credito.service.listener;

import com.analise.credito.domain.Proposta;
import com.analise.credito.service.analiseCredito.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListenerImpl implements PropostaPendenteListener {

    private final AnaliseCreditoService analiseCreditoService;

    public PropostaPendenteListenerImpl(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void recebeMensagemToPropostaPendente(Proposta proposta) {
        this.analiseCreditoService.analisar(proposta);
    }
}
