package com.gerenciadortarefas.exceptions;

public class TarefaExistenteException extends RuntimeException {

    public TarefaExistenteException() {
        super();
    }

    public TarefaExistenteException(String mensagem) {
        super(mensagem);
    }
}
