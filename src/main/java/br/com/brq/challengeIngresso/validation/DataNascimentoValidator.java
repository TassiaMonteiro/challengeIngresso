package br.com.brq.challengeIngresso.validation;

import br.com.brq.challengeIngresso.domain.exception.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, String> {


    @Override
    public void initialize(DataNascimento constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    public static LocalDate buscarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d = LocalDate.parse(data, formatter);
            return d;
        } catch (Exception e){
            throw new NegocioException("O formato " + data + " não é válido. Formato válido: \"yyyy-MM-dd\".");
        }

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String dataNascimento = value;

        if (StringUtils.isBlank(dataNascimento)){
            return true;
        }

        LocalDate dataAtual = LocalDate.now();
        LocalDate data = buscarData(dataNascimento);

        if (dataAtual.isBefore(data)){
            return false;
        }
        return true;
    }

}

