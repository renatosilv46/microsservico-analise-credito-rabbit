package com.analise.credito.service.listener;

import com.analise.credito.domain.Proposta;

public interface PropostaPendenteListener {
    void recebeMensagemToPropostaPendente(Proposta proposta);
}
