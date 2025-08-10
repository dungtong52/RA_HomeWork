package com.ra.validator;

import com.ra.model.Student;
import com.ra.service.StudentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidEmail implements ConstraintValidator<IsValidEmail, String> {
    @Autowired
    private StudentService studentService;

    @Override
    public void initialize(IsValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
//        String idParam = request.getParameter("id");
//        if (idParam != null) {
//            long id = Long.parseLong(idParam);
//            Student existing = studentService.getStudentByEmail(email);
//            // Nếu chưa tồn tại hoặc tồn tại nhưng là chính student đang edit → hợp lệ
//            return existing == null || existing.getId() == id;
//        }
        return !studentService.existEmail(email);
    }
}
