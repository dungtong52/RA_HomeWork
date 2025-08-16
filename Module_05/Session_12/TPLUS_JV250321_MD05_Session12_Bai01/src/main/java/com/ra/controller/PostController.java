package com.ra.controller;

import com.ra.dto.PostDto;
import com.ra.mapper.PostMapper;
import com.ra.model.Post;
import com.ra.model.User;
import com.ra.service.CloudinaryService;
import com.ra.service.PostService;
import com.ra.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public PostController(PostService postService, CloudinaryService cloudinaryService, UserService userService) {
        this.postService = postService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public String listPosts(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("posts", postService.getAllPostByUser(currentUser.getId()));
        return "postList";
    }

    @GetMapping("/posts/add")
    public String showAddPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "addPost";
    }

    @PostMapping("/posts/add")
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

    @GetMapping("/posts/delete/{id}")
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

    @GetMapping("/friends/{id}")
    public String showFriendPost(@PathVariable("id") Long friendId, Model model) {
        List<Post> postList = postService.getAllPostByUser(friendId);
        model.addAttribute("posts", postList);
        model.addAttribute("friendId", friendId);
        return "friendPosts";
    }

    @GetMapping("/posts/write-on-the-wall/{friendId}")
    public String showWriteOnFriendWall(@PathVariable("friendId") Long friendId,
                                        HttpSession session,
                                        Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("friendId", friendId);
        model.addAttribute("postDto", new PostDto());
        return "writeOnFriendPost";
    }

    @PostMapping("/posts/write-on-the-wall/{friendId}")
    public String writeOnFriendWall(@PathVariable("friendId") Long friendId,
                                    @Valid @ModelAttribute("postDto") PostDto postDto,
                                    BindingResult result,
                                    HttpSession session,
                                    Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("friendId", friendId);
            return "writeOnFriendPost";
        }

        try {
            User friend = userService.findById(friendId);
            if (postDto.getImage() != null && !postDto.getImage().isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(postDto.getImage());

                Post post = PostMapper.toModel(postDto, imageUrl, currentUser);
                post.setImage(imageUrl);
                post.setUser(friend);
                post.setFriend(currentUser);

                postService.save(post);
            }
            return "redirect:/friends/" + friendId;
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("friendId", friendId);
            return "writeOnFriendPost";
        }
    }
}
