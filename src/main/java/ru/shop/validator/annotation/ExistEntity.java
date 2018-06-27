package ru.shop.validator.annotation;

import ru.shop.validator.handler.ExistEntityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistEntityValidator.class)
@Documented
public @interface ExistEntity {

    Class<?> entityClass();

    String message() default "{validation.ExistEntity.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
