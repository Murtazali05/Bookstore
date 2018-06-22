package ru.shop.validator.annotation;

import ru.shop.validator.handler.PubYearConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.TYPE } )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PubYearConstraintValidator.class)
@Documented
public @interface PubYear {

    String message() default "{год издания должен быть больше 0 и меньше или равен текущему году}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
