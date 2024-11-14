package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class TipoDocumentoNaoPodeSerNuloException extends RuntimeException {

    public TipoDocumentoNaoPodeSerNuloException() {
        super("Tipo de documento não pode ser nulo");
    }

    public TipoDocumentoNaoPodeSerNuloException(String message) {
        super(message);
    }

}
