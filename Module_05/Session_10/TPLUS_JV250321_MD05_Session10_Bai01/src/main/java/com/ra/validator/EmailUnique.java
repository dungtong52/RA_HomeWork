package com.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({FIELD})
@Retention(RUNTIME)
public @interface EmailUnique {
    String message() default "Email đã tồn tại!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
