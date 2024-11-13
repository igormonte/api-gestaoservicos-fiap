package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public record EnderecoDto (
    String rua,
    Integer numero,
    String bairro,
    String cidade,
    String estado,
    String cep){

}
