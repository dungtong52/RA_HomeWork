package com.ra.controller;

import com.ra.dto.UserLoginDto;
import com.ra.dto.UserRegisterDto;
import com.ra.mapper.UserMapper;
import com.ra.model.User;
import com.ra.service.CloudinaryService;
import com.ra.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserController(UserService userService, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    // Register, Login

    @GetMapping("/auth/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/auth/register")
    public String processRegister(@Valid @ModelAttribute("userRegisterDto") UserRegisterDto dto,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            String avatarUrl = cloudinaryService.uploadFile(dto.getAvatar());
            User user = UserMapper.toModel(dto, avatarUrl);
            userService.register(user);
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/auth/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "login";
    }

    @PostMapping("/auth/login")
    public String processLogin(@ModelAttribute("userLoginDto") UserLoginDto dto, HttpSession session, Model model) {
        User user = userService.login(dto.getEmail(), dto.getPassword());

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/user";
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng");
            return "login";
        }
    }

    @GetMapping("/auth/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    // User

    @GetMapping("/user")
    public String profile(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/user")
    public String updateProfile(@ModelAttribute("user") User updateUser,
                                @RequestParam("avatarImg") MultipartFile avatarImg,
                                HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        currentUser.setFullName(updateUser.getFullName());
        currentUser.setEmail(updateUser.getEmail());
        currentUser.setPassword(updateUser.getPassword());

        // Up lai anh
        if (avatarImg != null && !avatarImg.isEmpty()) {
            try {
                String avatarUrl = cloudinaryService.uploadFile(avatarImg);
                currentUser.setAvatar(avatarUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userService.update(currentUser);
        session.setAttribute("loggedInUser", currentUser);
        return "redirect:/user";
    }

    @GetMapping("/users/search")
    public String showSearchFriendForm(){
        return "searchFriend";
    }

    @PostMapping("/user/search")
    public String searchFriend(@RequestParam("email") String email,
                               HttpSession session,
                               Model model){
        User currentUser = (User) session.getAttribute("loggedInUser");
        User foundUser = userService.findByEmail(email);
        if(foundUser!=null && !foundUser.getId().equals(currentUser.getId())){
            boolean isAlreadyFriend = currentUser.getFriends().contains(foundUser);
            model.addAttribute("foundUser", foundUser);
            model.addAttribute("alreadyFriend", isAlreadyFriend);
        } else {
            model.addAttribute("message", "Không tìm thấy người dùng!");
        }
        return "searchFriend";
    }

    @GetMapping("/user/add-friend")
    public String addFriend(@RequestParam("friendId") Long friendId,
                            HttpSession session){
        User currentUser = (User) session.getAttribute("loggedInUser");
        User friend = userService.findByEmail(userService.findById(friendId).getEmail());

        userService.addFriend(currentUser, friend);

        return "redirect:/profile";
    }
}
