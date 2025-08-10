package com.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint( validatedBy = {ValidEmail.class} )
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidEmail {
    String message() default "Email đã tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
