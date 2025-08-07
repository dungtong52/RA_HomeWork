package com.ra.controller;

import com.ra.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/users"})
public class UserController {
    @GetMapping
    public String initUsers(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "userForm";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("error","Đăng ký thất bại");
            model.addAttribute("user",user);
            return "userForm";
        }else {
            model.addAttribute("success","Đăng ký thành công");
            model.addAttribute("user",user);
            return "result";
        }
    }

}
