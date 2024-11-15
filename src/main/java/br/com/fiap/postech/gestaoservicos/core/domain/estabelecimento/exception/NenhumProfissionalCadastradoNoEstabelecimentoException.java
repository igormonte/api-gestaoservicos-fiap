package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception;

public class NenhumProfissionalCadastradoNoEstabelecimentoException extends RuntimeException {

    public NenhumProfissionalCadastradoNoEstabelecimentoException() {
        super("Nenhum profissional cadastrado para o estabelecimento!");
    }

    public NenhumProfissionalCadastradoNoEstabelecimentoException(String message) {
        super(message);
    }

}
