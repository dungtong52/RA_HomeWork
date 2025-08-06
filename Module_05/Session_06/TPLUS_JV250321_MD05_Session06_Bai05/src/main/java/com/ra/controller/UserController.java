package com.ra.controller;

import com.ra.model.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView listAllUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
