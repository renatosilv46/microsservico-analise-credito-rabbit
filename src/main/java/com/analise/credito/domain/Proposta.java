package com.analise.credito.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposta {
    private Long id;
    private double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovada;
    private boolean integrada;
    private String observacao;
    private Usuario usuario;
}
