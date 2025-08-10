package com.ra.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ra.entity.userForm;

@Controller
@RequestMapping(value = {"/","phone"})
public class phoneController {

    @GetMapping()
    public String phoneNumber(Model model){
       model.addAttribute("userForm" ,new userForm());
       return "phone-form";
    };

    @PostMapping("/phoneNumber")
    public String check(@Valid @ModelAttribute("userForm") userForm userForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("message" ,"not vaild");
            return "phone-form";

        }
        model.addAttribute("message" ,"Phone Number Pass");
        return "home";
    }

}
