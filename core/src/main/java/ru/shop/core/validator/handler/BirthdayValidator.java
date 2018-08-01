package ru.shop.core.validator.handler;

import ru.shop.core.validator.annotation.Birthday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

public class BirthdayValidator implements ConstraintValidator<Birthday, Object> {

    @Override
    public boolean isValid(Object birthday, ConstraintValidatorContext context) {
        if (birthday instanceof Date)
            return (((Date) birthday).getTime() > 0 && ((Date) birthday).getTime() < System.currentTimeMillis());

        return false;
    }
}
