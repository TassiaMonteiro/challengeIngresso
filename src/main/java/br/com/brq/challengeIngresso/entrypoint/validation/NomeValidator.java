package br.com.brq.challengeIngresso.entrypoint.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NomeValidator implements ConstraintValidator<NomeCompleto, String> {

    @Override
    public void initialize(NomeCompleto constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    String regex = "^(?!.*(.)\\1{2,}).*[a-zA-Z]{2,}( {1,2}[a-zA-Z]{2,}){0,}$";

    Pattern p = Pattern.compile(regex);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String nome = String.valueOf(value);

        Matcher m = p.matcher(nome);
        return m.matches();
    }
}

