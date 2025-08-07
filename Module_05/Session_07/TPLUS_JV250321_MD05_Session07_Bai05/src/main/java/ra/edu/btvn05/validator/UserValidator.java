package ra.edu.btvn05.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "username.empty", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "email.empty", "Email is required");

        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!user.getEmail().matches(regexEmail)) {
            errors.rejectValue("email", "email.invalid", "Email is invalid");
        }
    }
}
