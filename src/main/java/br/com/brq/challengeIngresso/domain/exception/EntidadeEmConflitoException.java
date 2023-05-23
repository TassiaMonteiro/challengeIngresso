package br.com.brq.challengeIngresso.domain.exception;

public class EntidadeEmConflitoException extends NegocioException{

    public EntidadeEmConflitoException(String mensagem){
        super(mensagem);
    }
}
