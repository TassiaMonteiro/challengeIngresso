package br.com.brq.challengeIngresso.usecase.exception;

public class ApiExternaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ApiExternaException(String message) {
        super(message);
    }
}
