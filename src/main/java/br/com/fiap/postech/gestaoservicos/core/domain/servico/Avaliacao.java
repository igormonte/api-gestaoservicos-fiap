package br.com.fiap.postech.gestaoservicos.core.domain.servico;

import br.com.fiap.postech.gestaoservicos.core.domain.servico.exception.NotaInvalidaException;
import lombok.Data;

import java.util.UUID;

@Data
public class Avaliacao {

    private UUID id;
    private Integer nota;
    private String comentario;

    public Avaliacao(
            Integer nota,
            String comentario) {
        if(nota > 10 || nota < 0) {
            throw new NotaInvalidaException();
        }
        this.nota = nota;
        this.comentario = comentario;
    }

}
