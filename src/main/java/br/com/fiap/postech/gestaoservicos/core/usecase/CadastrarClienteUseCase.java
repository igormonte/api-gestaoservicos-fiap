package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;

import java.time.LocalDate;

public interface CadastrarClienteUseCase {

    ClienteEntity executa(String nome,
                          String email,
                          String numeroDocumento,
                          TipoDocumento tipoDocumento,
                          LocalDate dataNascimento);

}
