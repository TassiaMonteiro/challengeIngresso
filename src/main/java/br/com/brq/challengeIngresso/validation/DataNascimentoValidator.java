package br.com.brq.challengeIngresso.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, String> {


    @Override
    public void initialize(DataNascimento constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String dataNascimento = value;

        LocalDate dataAtual = LocalDate.now();
        LocalDate data = buscarData(dataNascimento);

        if (dataAtual.isBefore(data)){
            return false;
        }
        return true;
    }

    public static LocalDate buscarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d = LocalDate.parse(data, formatter);
            return d;
        } catch (Exception e){
            throw new RuntimeException("O formato " + data + " não é válido. Formato válido: \"yyyy-MM-dd\".");
        }

    }

}

