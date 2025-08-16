package com.ra.controller;

import com.ra.dto.PostDto;
import com.ra.mapper.PostMapper;
import com.ra.model.Post;
import com.ra.model.User;
import com.ra.service.CloudinaryService;
import com.ra.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CloudinaryService cloudinaryService;

    public PostController(PostService postService, CloudinaryService cloudinaryService) {
        this.postService = postService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String listPosts(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("posts", postService.getAllPostByUser(currentUser.getId()));
        return "postList";
    }

    @GetMapping("/add")
    public String showAddPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@Valid @ModelAttribute("postDto") PostDto dto,
                          BindingResult result,
                          HttpSession session,
                          Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        if (result.hasErrors()) {
            return "addPost";
        }
        try {
            String imageUrl = cloudinaryService.uploadFile(dto.getImage());
            Post post = PostMapper.toModel(dto, imageUrl, currentUser);
            postService.save(post);
            return "redirect:/posts";
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "addPost";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        Post post = postService.findById(id);
        if (post != null && post.getUser().getId().equals(currentUser.getId())) {
            postService.delete(id);
        }
        return "redirect:/posts";
    }
}
