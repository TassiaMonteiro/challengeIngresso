package br.com.brq.challengeIngresso.dataprovider.enums;

import lombok.Getter;

@Getter
public enum Sexo {

    M("1", "M"),
    F("2", "F"),
    B("3", "B"),
    N("4", "N");

    private String valor;
    private String sigla;

    Sexo(String valor, String sigla) {
        this.valor = valor;
        this.sigla = sigla;
    }

    public static String buscarValor(String sigla){
        for(Sexo sexo : values()){
            if (sexo.sigla.equalsIgnoreCase(sigla)){
                return sexo.valor;
            }
        }
        return null;
    }

    public static String buscarSigla(String valor){
        for(Sexo sexo : values()){
            if (sexo.valor.equalsIgnoreCase(valor)){
                return sexo.sigla;
            }
        }
        return null;
    }
}
