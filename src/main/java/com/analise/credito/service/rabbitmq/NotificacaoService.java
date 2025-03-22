package com.analise.credito.service.rabbitmq;

import com.analise.credito.domain.Proposta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacaoService {

    private static final String IDENTIFICADOR_OPERACAO_NOTIFICACAO_SERVICE =
            "[NotificacaoService]";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notificar(String exchange, Proposta proposta) {
        log.info("{} Notificando queue proposta-concluida | Exchange: {}",
                IDENTIFICADOR_OPERACAO_NOTIFICACAO_SERVICE, exchange);
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
