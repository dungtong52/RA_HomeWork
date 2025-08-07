package ra.edu.entity;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class ValidatorPhoneNumber implements ConstraintValidator<ValidatorPhoneNumber, String>, Annotation {
    @Override
    public void initialize(ValidatorPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches("^0//d{9,10}$");
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
