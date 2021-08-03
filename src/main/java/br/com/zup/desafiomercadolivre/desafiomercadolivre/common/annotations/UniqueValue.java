package br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention( RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Foi encontrado mais de um cadastro com o mesmo atributo!";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();
    Class<?> domainClass();
}
