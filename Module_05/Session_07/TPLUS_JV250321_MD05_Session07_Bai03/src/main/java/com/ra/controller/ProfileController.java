package com.ra.controller;

import com.ra.model.UserProfile;
import com.ra.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String uploadAvatar(@ModelAttribute("userProfile") UserProfile userProfile, Model model) {
        System.out.println("");

        MultipartFile avatar = userProfile.getAvatar();
        if (avatar != null && !avatar.isEmpty()) {
            String avatarPath = uploadService.uploadFile(avatar);
            model.addAttribute("username", userProfile.getUsername());
            model.addAttribute("avatarPath", avatarPath);
        } else {
            model.addAttribute("error", "Vui lòng chọn một file ảnh.");
        }
        return "profileResult";
    }
}
