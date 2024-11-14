package br.com.fiap.postech.gestaoservicos.adapter.handler;


import br.com.fiap.postech.gestaoservicos.adapter.handler.dto.ErrorResponse;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception.ClienteNaoEncontradoException;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception.ClienteNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EnderecoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EstabelecimentoNaoEncontradoException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EstabelecimentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.NomeInvalidoException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.*;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.*;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.exception.NotaInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }

        Collections.sort(errors);
        ErrorResponse errorResponse =
                new ErrorResponse("Validation error", errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteNaoEncontradoException(
            ClienteNaoEncontradoException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(ClienteNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleClienteNaoPodeSerNuloException(
            ClienteNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(EnderecoNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleEnderecoNaoPodeSerNuloException(
            EnderecoNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);

    }

    @ExceptionHandler(EstabelecimentoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEstabelecimentoNaoEncontradoException(
            EstabelecimentoNaoEncontradoException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);

    }

    @ExceptionHandler(EstabelecimentoNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleEstabelecimentoNaoPodeSerNuloException(
            EstabelecimentoNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);

    }

    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNomeInvalidoException(
            NomeInvalidoException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(DataNascimentoNaoPodeSerNulaException.class)
    public ResponseEntity<ErrorResponse> handleDataNascimentoNaoPodeSerNulaException(
            DataNascimentoNaoPodeSerNulaException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(DocumentoNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleDocumentoNaoPodeSerNuloException(
            DocumentoNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleEmailNaoPodeSerNuloException(
            EmailNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(NomeNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleNomeNaoPodeSerNuloException(
            NomeNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(NumeroDoDocumentoNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleNumeroDoDocumentoNaoPodeSerNuloException(
            NumeroDoDocumentoNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(PessoaNaoPodeSerNulaException.class)
    public ResponseEntity<ErrorResponse> handlePessoaNaoPodeSerNulaException(
            PessoaNaoPodeSerNulaException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(TipoDocumentoNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleTipoDocumentoNaoPodeSerNuloException(
            EspecialidadeJaExisteException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleAgendamentoNaoEncontradoException(
            EspecialidadeJaExisteException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(EspecialidadeJaExisteException.class)
    public ResponseEntity<ErrorResponse> handleEspecialidadeJaExisteException(
            EspecialidadeJaExisteException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(EspecialidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleEspecialidadeNaoEncontradaException(
            EspecialidadeNaoEncontradaException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(EspecialidadeNaoPodeSerNulaException.class)
    public ResponseEntity<ErrorResponse> handleEspecialidadeNaoPodeSerNulaException(
            EspecialidadeNaoPodeSerNulaException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(NomeEspecialidadeNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleNomeEspecialidadeNaoPodeSerNuloException(
            NomeEspecialidadeNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(ProfissionalNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleProfissionalNaoEncontradoException(
            ProfissionalNaoEncontradoException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(ProfissionalNaoPodeSerNuloException.class)
    public ResponseEntity<ErrorResponse> handleProfissionalNaoPodeSerNuloException(
            ProfissionalNaoPodeSerNuloException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(NotaInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleNotaInvalidaException(
            NotaInvalidaException e) {
        ErrorResponse errorResponse =
                new ErrorResponse("Runtime error", List.of(e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

}