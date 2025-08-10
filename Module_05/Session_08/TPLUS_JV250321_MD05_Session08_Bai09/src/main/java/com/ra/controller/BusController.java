package com.ra.controller;

import com.ra.model.Bus;
import com.ra.model.BusType;
import com.ra.repository.CloudinaryService;
import com.ra.service.BusService;
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
public class BusController {
    private final BusService busService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public BusController(BusService busService, CloudinaryService cloudinaryService) {
        this.busService = busService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/")
    public String showAllBus(Model model) {
        List<Bus> busList = busService.getAllBus();
        model.addAttribute("buses", busList);
        return "list";
    }

    @GetMapping("/add")
    public String initAddBus(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("busTypes", BusType.values());
        return "add";
    }

    @PostMapping("/add")
    public String addBus(@Valid @ModelAttribute("bus") Bus bus,
                         BindingResult result,
                         @RequestParam("imageFile") MultipartFile file,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("busTypes", BusType.values());
            return "add";
        }

        try {
            String imageUrl = cloudinaryService.uploadFile(file);
            bus.setImage(imageUrl);
            System.out.println(123);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "Upload ảnh thất bại");
            return "add";
        }

        boolean addSuccess = busService.addBus(bus);
        if (!addSuccess) {
            model.addAttribute("errorMsg", "Thêm không thành công");
            return "add";
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String initEditBus(@PathVariable("id") int id, Model model) {
        Bus bus = busService.getBusById(id);
        model.addAttribute("bus", bus);
        model.addAttribute("busTypes", BusType.values());
        return "update";
    }

    @PostMapping("/edit")
    public String editBus(@Valid @ModelAttribute("bus") Bus bus,
                          BindingResult result,
                          @RequestParam("imageFile") MultipartFile file,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("busTypes", BusType.values());
            return "update";
        }
        System.out.println("Cạy ở POST edit trước khi up file");
        try {
            String imageFile = cloudinaryService.uploadFile(file);
            bus.setImage(imageFile);
            System.out.println(456);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "Vui lòng chọn ảnh để upload");
            return "update";
        }

        boolean editSuccess = busService.updateBus(bus);
        if (!editSuccess) {
            model.addAttribute("errorMsg", "Cập nhật không thành công");
            return "update";
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Cập nhật thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleteSuccess = busService.deleteBus(id);
        if (!deleteSuccess) {
            redirectAttributes.addFlashAttribute("errorMsg", "Xóa không thành công");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa thành công");
        }
        return "redirect:/";
    }
}
