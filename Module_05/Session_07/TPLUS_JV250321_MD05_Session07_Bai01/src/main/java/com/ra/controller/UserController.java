package com.ra.controller;

import com.ra.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @GetMapping(value = {"/userForm", "/"})
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "result";
    }
}
