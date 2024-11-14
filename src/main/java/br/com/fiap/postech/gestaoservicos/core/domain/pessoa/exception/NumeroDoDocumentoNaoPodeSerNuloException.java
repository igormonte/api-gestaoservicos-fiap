package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class NumeroDoDocumentoNaoPodeSerNuloException extends RuntimeException {

    public NumeroDoDocumentoNaoPodeSerNuloException() {
        super("Número do documento não pode ser nulo");
    }

    public NumeroDoDocumentoNaoPodeSerNuloException(String message) {
        super(message);
    }

}
