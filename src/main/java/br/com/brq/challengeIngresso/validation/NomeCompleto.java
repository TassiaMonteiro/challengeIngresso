package br.com.brq.challengeIngresso.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NomeValidator.class)
public @interface NomeCompleto {

    String message() default "O nome informado não está em um formato válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
