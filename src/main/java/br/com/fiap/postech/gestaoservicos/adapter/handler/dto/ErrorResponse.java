package br.com.fiap.postech.gestaoservicos.adapter.handler.dto;

import java.util.List;

public record ErrorResponse(
    String message,
    List<String>errors) {
}
