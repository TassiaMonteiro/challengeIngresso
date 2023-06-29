package br.com.brq.challengeIngresso.usecase.exception;

public class CepNaoEncontrado extends RuntimeException {

    public CepNaoEncontrado(String message) {
        super(message);
    }
}
