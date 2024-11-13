package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import java.util.UUID;

public record AdicionarProfissionalDto(UUID idEstabelecimento, UUID idProfissional) {
}
