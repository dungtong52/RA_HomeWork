package com.ra.controller;

import com.ra.entity.Review;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviewForm";
    }

    @PostMapping("/submitReview")
    public String submitReview(@Valid @ModelAttribute("review") Review review,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reviewForm";
        }
        model.addAttribute("review", review);
        return "reviewResult";
    }
}
