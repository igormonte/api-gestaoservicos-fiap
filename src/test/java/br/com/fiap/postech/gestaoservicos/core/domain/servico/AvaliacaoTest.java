package br.com.fiap.postech.gestaoservicos.core.domain.servico;

import br.com.fiap.postech.gestaoservicos.core.domain.servico.exception.NotaInvalidaException;
import br.com.fiap.postech.gestaoservicos.utils.entity.AvaliacaoHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class AvaliacaoTest {

    @Test
    void deveCriarAvaliacao() {
        Integer nota = 10;
        String comentario = "Excelente";
        Avaliacao avaliacao = new Avaliacao(nota, comentario);
        assertThat(avaliacao.getNota()).isEqualTo(nota);
        assertThat(avaliacao.getComentario()).isEqualTo(comentario);

    }

    @Test
    void deveCriarAvaliacao_GeraExcessaoSeNotaSuperiorA10() {
        Integer nota = 11;
        String comentario = "Excelente";
        assertThatThrownBy(() ->
                new Avaliacao(nota, comentario))
                .isInstanceOf(NotaInvalidaException.class);
    }

    @Test
    void deveCriarAvaliacao_GeraExcessaoSeNotaInferiorA0() {
        Integer nota = -1;
        String comentario = "Excelente";
        assertThatThrownBy(() ->
                new Avaliacao(nota, comentario))
                .isInstanceOf(NotaInvalidaException.class);

    }
}
