package com.gerenciadortarefas.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciadortarefas.response.ErrorResponse;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidacaoCamposHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> tratarValidacoes(MethodArgumentNotValidException ex) {

        List<Map<String, String>> listErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> {

                    Map<String, String> errors = new HashMap<>();
                    errors.put("campo", obterNomePropriedade(erro));
                    errors.put("descricao", erro.getDefaultMessage());

                    return errors;

                })
                .toList();

        ErrorResponse response = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .errors(listErrors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String obterNomePropriedade(final FieldError error) {
        if (error.contains(ConstraintViolation.class)) {

            try {
                final ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
                final Field field;

                field = violation.getRootBeanClass().getDeclaredField(error.getField());

                final JsonProperty anotation =  field.getAnnotation(JsonProperty.class);

                if (anotation != null && anotation.value() != null && !anotation.value().isEmpty()) {
                    return anotation.value();
                }
            } catch (Exception e) {}

        }
        return error.getField();
    }
}
