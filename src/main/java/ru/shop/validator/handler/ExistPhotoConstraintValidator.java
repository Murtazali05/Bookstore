package ru.shop.validator.handler;

import org.springframework.beans.factory.annotation.Autowired;
import ru.shop.service.PhotoService;
import ru.shop.validator.annotation.ExistPhoto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistPhotoConstraintValidator implements ConstraintValidator<ExistPhoto, Integer> {
    @Autowired
    private PhotoService photoService;

    @Override
    public void initialize(ExistPhoto constraintAnnotation) {
        // EMPTY METHOD
    }

    @Override
    public boolean isValid(Integer photoId, ConstraintValidatorContext context) {
        return photoService.existPhotoById(photoId);
    }
}
