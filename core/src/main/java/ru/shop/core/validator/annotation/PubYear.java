package ru.shop.core.validator.annotation;

import ru.shop.core.validator.handler.PubYearValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.TYPE } )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PubYearValidator.class)
@Documented
public @interface PubYear {

    String message() default "{validation.PubYear.message}"; // год издания должен быть больше 0 и меньше или равен текущему году

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
