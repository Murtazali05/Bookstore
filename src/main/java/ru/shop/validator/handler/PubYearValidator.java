package ru.shop.validator.handler;

import ru.shop.validator.annotation.PubYear;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class PubYearValidator implements ConstraintValidator<PubYear, Integer> {

    @Override
    public boolean isValid(Integer pubYear, ConstraintValidatorContext context) {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return pubYear > 0 && pubYear <= currentYear;
    }

}
