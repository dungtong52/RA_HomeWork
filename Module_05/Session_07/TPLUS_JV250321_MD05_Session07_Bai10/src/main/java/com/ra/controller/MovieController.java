package com.ra.controller;

import com.ra.model.Movie;
import com.ra.service.CloudinaryService;
import com.ra.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class MovieController {
    private final MovieService movieService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public MovieController(MovieService movieService, CloudinaryService cloudinaryService) {
        this.movieService = movieService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/")
    public String showAllMovie(Model model) {
        List<Movie> movieList = movieService.findAll();
        model.addAttribute("movies", movieList);
        return "list";
    }

    @GetMapping("/add")
    public String initAddMovie(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "add";
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute("movie") Movie movie,
                           BindingResult result,
                           @RequestParam("imageFile") MultipartFile uploadFile,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add";
        }
        String imageUrl = null;
        try {
            imageUrl = cloudinaryService.uploadFile(uploadFile);
            movie.setPoster(imageUrl);
        } catch (IOException e) {
            model.addAttribute("error", "Có lỗi khi upload file");
            return "add";
        }
        
        boolean success = movieService.saveMovie(movie);
        if (!success) {
            model.addAttribute("error", "Thêm mới không thành công");
            return "add";
        } else {
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String initEditMovie(@PathVariable("id") int id, Model model) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null) {
            model.addAttribute("error", "Không tìm thấy phim có id:" + id);
            return "list";
        } else {
            model.addAttribute("movie", movie);
            return "edit";
        }
    }

    @PostMapping("/edit")
    public String editMovie(@Valid @ModelAttribute("movie") Movie movie,
                            BindingResult result,
                            @RequestParam("imageFile") MultipartFile uploadFile,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "edit";
        }

        try {
            String imageUrl = cloudinaryService.uploadFile(uploadFile);
            movie.setPoster(imageUrl);
        } catch (IOException e) {
            model.addAttribute("error", "Có lỗi khi upload file");
            return "edit";
        }

        boolean success = movieService.updateMovie(movie);
        if (!success) {
            model.addAttribute("error", "Sửa không thành công");
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean success = movieService.deleteMovie(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Xóa không thành công");
        } else {
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        }
        return "redirect:/";
    }
}
