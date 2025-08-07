package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.entity.userForm;

@Controller
@RequestMapping(value = {"/","phoneNumberController"})
public class phoneNumberController {


    @GetMapping()
    public String phoneNumber(Model model){
       model.addAttribute("userForm" ,new userForm());
       return "checknumberphone";
    };

    @PostMapping("/phoneNumber")
    public String check(@Valid @ModelAttribute("userForm") userForm userForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("message" ,"not vaild");
            return "checknumberphone";

        }
        model.addAttribute("message" ,"Phone Number Pass");
        return "home";
    }



}
