package com.ra.controller;

import com.ra.model.User;
import com.ra.model.UserLogin;
import com.ra.service.SeedService;
import com.ra.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class GameController {
    private final UserService userService = new UserService();
    private final SeedService seedService = new SeedService();

    @GetMapping("/")
    public String index() {
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.register(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Tên người dùng đã tồn tại.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            UserLogin.user = user;
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            return "login";
        }
    }

    @GetMapping("/shop")
    public String showShop(Model model) {
        if (UserLogin.user == null) {
            return "redirect:/login";
        }
        model.addAttribute("seeds", seedService.getAllSeeds());
        return "shop";
    }

    @GetMapping("/home")
    public String home() {
        if (UserLogin.user == null) {
            return "redirect:/register";
        }
        return "home";
    }
}
