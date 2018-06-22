package ru.shop.validator.annotation;

import ru.shop.validator.handler.ExistPhotoConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistPhotoConstraintValidator.class)
@Documented
public @interface ExistPhoto {

    String message() default "{validation.ExistPhoto.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
