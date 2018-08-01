package ru.shop.core.validator.annotation;

import ru.shop.core.validator.handler.BirthdayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.TYPE } )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidator.class)
@Documented
public @interface Birthday {

    String message() default "{validation.Birthday.incorrect.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
