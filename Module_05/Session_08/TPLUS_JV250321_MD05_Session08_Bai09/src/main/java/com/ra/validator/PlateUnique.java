package com.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(
        validatedBy = {PlateUniqueValidator.class}
)
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlateUnique {
    String message() default "Biển số xe đã tồn tại. Nhập biển số khác!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
