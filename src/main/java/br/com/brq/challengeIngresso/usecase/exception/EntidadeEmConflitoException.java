package br.com.brq.challengeIngresso.usecase.exception;

public class EntidadeEmConflitoException extends NegocioException {

    public EntidadeEmConflitoException(String mensagem){
        super(mensagem);
    }
}
