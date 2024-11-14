package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class DocumentoNaoPodeSerNuloException extends RuntimeException {

    public DocumentoNaoPodeSerNuloException() {
        super("Documento não pode ser nulo");
    }

    public DocumentoNaoPodeSerNuloException(String message) {
        super(message);
    }

}
