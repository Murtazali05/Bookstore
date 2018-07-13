package ru.shop.validator.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.context.WebApplicationContext;
import ru.shop.persistense.repository.UserRepository;
import ru.shop.validator.annotation.ExistEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class ExistEntityValidator implements ConstraintValidator<ExistEntity, Object> {

    private JpaRepository jpaRepository;

    @Autowired
    private WebApplicationContext appContext;

    @Override
    public void initialize(ExistEntity constraintAnnotation) {
        Repositories repositories = new Repositories(appContext);

        Class<?> entityClass = constraintAnnotation.entityClass();

        jpaRepository = (JpaRepository) repositories.getRepositoryFor(entityClass).orElse(null);

        if (jpaRepository == null)
            throw new RuntimeException("Cannot find JpaRepository for " + entityClass.getName());

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object entityObject, ConstraintValidatorContext context) {

        if (entityObject == null)
            return true;
        else if (entityObject instanceof Integer) {
            return jpaRepository.existsById(entityObject);
        } else if (entityObject instanceof String) {
            UserRepository userRepository = (UserRepository) jpaRepository;
            Object result = userRepository.findByEmail(entityObject.toString());
            return (result != null);
        } else if (entityObject instanceof Collection<?>) {
            for (Integer id : (Collection<Integer>) entityObject) {
                if (!jpaRepository.existsById(id))
                    return false;
            }
            return true;
        } else
            throw new IllegalArgumentException("A parameter with this type=" + entityObject.getClass().getTypeName() + " is not processed by the annotation");

    }

}
