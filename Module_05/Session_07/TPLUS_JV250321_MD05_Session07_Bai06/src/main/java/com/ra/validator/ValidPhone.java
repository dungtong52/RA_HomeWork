package com.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { ValidatorPhoneNumber.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidPhone {

    String message() default "Phone không đúng định dạng";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
