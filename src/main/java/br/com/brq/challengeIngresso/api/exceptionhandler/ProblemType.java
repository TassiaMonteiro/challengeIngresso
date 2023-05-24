package br.com.brq.challengeIngresso.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    EM_CONFLITO("/em-conflito", "Requisicao em Conflito"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_INTERNO("/erro-de-sistema", "Erro interno"),
    MENSAGEM_IMCOMPREENSIVEL("/mensagem-imcompreensivel", "Mensagem imcompreensível"),
    NAO_PERMITIDO("/metodo-nao-permitido", "Método nao permitido");

    private String title;
    private String type;

    ProblemType(String type, String title){
        this.type = type;
        this.title = title;
    }
}
