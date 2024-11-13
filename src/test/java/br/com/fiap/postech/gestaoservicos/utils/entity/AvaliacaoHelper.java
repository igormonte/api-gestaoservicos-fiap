package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.servico.Avaliacao;

public class AvaliacaoHelper {

    public static Avaliacao getAvaliacao() {
        return new Avaliacao(10, "Excelente");

    }
}
