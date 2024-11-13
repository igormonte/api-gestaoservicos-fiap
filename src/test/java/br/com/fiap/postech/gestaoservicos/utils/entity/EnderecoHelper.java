package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.EnderecoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Endereco;

public class EnderecoHelper {

    public static Endereco criarEndereco() {
        return new Endereco("Rua Teste",
                123,
                "Centro",
                "São Paulo",
                "SP",
                "12345678");
    }

    public static EnderecoDto criarEnderecoDto() {
        return new EnderecoDto("Rua Teste",
                123,
                "Centro",
                "São Paulo",
                "SP",
                "12345678");
    }
}
