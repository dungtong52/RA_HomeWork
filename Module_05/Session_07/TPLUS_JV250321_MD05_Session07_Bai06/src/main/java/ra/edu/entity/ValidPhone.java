package ra.edu.entity;

import org.springframework.validation.Errors;

import java.lang.annotation.Annotation;

public interface ValidPhone {
    boolean supports(Class<? extends Annotation> annotation);
    void validate(Object target, Errors errors);
}
