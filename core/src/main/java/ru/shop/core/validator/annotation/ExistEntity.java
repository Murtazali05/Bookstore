package ru.shop.core.validator.annotation;

import ru.shop.core.validator.handler.ExistEntityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistEntityValidator.class)
@Documented
public @interface ExistEntity {

    Class<?> entityClass();

    String message() default "{validation.ExistEntity.NotFound.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
