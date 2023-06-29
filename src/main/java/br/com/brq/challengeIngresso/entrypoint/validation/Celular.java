package br.com.brq.challengeIngresso.entrypoint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CelularValidator.class)
public @interface Celular {

    String message() default "O celular informado não está em um formato válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
