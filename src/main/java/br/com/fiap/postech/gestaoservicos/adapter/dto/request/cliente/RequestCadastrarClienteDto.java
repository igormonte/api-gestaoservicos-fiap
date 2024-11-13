package br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;

import java.time.LocalDate;

public record RequestCadastrarClienteDto(
        String nome,
        String email,
        String numeroDocumento,
        TipoDocumento tipoDocumento,
        LocalDate dataNascimento
) {
}
