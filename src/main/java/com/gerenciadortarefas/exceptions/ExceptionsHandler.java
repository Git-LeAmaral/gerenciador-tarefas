package com.gerenciadortarefas.exceptions;

import com.gerenciadortarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NaoPermitirExcluirException.class)
    public ResponseEntity<ErrorResponse> naoPermitirExcluirExceptionHandler(NaoPermitirExcluirException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("codigo", ErrosEnum.NAO_PERMITIDO_EXCLUIR.toString());
        response.put("mensagem", "Não é permitido escluir uma tarefa com status diferente de ESPERA");

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NaoPermitidoAlterarStatusException.class)
    public ResponseEntity<ErrorResponse> naoPermitidoAlterarStatusExceptionHandler(NaoPermitidoAlterarStatusException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("codigo", ErrosEnum.NAO_PERMITIDO_ALTERAR_STATUS.toString());
        response.put("mensagem", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TarefaExistenteException.class)
    public ResponseEntity<ErrorResponse> tarefaExistenteExceptionHandler(TarefaExistenteException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("codigo", ErrosEnum.TAREFA_EXISTENTE.toString());
        response.put("mensagem", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
