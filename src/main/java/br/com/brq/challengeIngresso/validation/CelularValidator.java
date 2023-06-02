package br.com.brq.challengeIngresso.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelularValidator implements ConstraintValidator<Celular, Long> {


    @Override
    public void initialize(Celular constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    private String regex = "^((1[1|2|3|4|5|6|7|8|9])|(2[1|2|4])|(3[1|2|3|4|5|7|8])|(4[1|2|3|4|5|6])|(5[1|3|4|5])|(6[1|2|3|4|5|6|7])|(7[1|3|4|5|7|9])|(8[1|2|3|5|6|7|8|9])|(9[1|2|3|4|5|6|7|8|9]))(9)[1-9]{8}$";
    private Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if (value == null){
            return true;
        }

        String celular = String.valueOf(value);

        Matcher matcher = pattern.matcher(celular);
        return matcher.matches();
    }
}

