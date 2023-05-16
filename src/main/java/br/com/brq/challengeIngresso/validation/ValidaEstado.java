package br.com.brq.challengeIngresso.validation;

import lombok.Getter;

@Getter
public enum ValidaEstado {

    Acre("AC"),
    Alagoas("AL"),
    Amapá("AP"),
    Amazonas("AM"),
    Bahia("BA"),
    Ceara("CE"),
    DistritoFederal("DF"),
    EspiritoSanto("ES"),
    Goias("GO"),
    MaranhAo("MA"),
    MatoGrossoDoSul("MS"),
    MatoGrosso("MT"),
    MinasGerais("MG"),
    Para("PA"),
    ParaIba("PB"),
    Parana("PR"),
    Pernambuco("PE"),
    Piaui("PI"),
    RioDeJaneiro("RJ"),
    RioGrandeDoNorte("RN"),
    RioGrandeDoSul("RS"),
    Rondonia("RO"),
    Roraima("RR"),
    SantaCatarina("SC"),
    SaoPaulo("SP"),
    Sergipe("SE"),
    Tocantins("TO");

    private String sigla;

    ValidaEstado(String sigla) {
        this.sigla = sigla;
    }

    public static Boolean isEstado(String sigla){
        for(ValidaEstado estado : values()){
            if (estado.sigla.equalsIgnoreCase(sigla)){
                return true;
            }
        }
        return false;
    }
}

