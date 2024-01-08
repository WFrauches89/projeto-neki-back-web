package com.api.neki.entities.exceptions;

public class NekiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NekiException(String mensagem){
        super(mensagem);
    }
}
