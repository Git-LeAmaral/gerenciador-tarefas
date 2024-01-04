package com.gerenciadortarefas.exceptions;

public class NaoPermitidoAlterarStatusException extends RuntimeException {

    public NaoPermitidoAlterarStatusException() {
        super();
    }

    public NaoPermitidoAlterarStatusException(String mensagem) {
        super(mensagem);
    }
}
