package ra.edu.btvn05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.btvn05.model.User;
import ra.edu.btvn05.validator.UserDTO;
import ra.edu.btvn05.validator.UserValidator;

@Controller
@RequestMapping(value = {"/","/register"})
public class UserController {
    @Autowired
    private UserValidator userValidator ;

    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "registerForm";
    }

    @PostMapping
    public String register(@ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }
        return "success";
    }
}
