package br.com.brq.challengeIngresso.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
