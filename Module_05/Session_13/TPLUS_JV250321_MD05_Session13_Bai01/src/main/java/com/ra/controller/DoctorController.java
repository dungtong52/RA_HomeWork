package com.ra.controller;

import com.ra.model.Doctor;
import com.ra.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String showAllDoctor(Model model) {
        List<Doctor> doctorList = doctorService.getAllDoctor();
        model.addAttribute("doctors", doctorList);
        return "doctorList";
    }

    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }

    @PostMapping("/add")
    public String addDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "addDoctor";
        }
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable("id") Long id,
                                     Model model) {
        Doctor doctor = doctorService.finfById(id);
        model.addAttribute("doctor", doctor);
        return "editDoctor";
    }

    @PostMapping("/edit")
    public String editDoctor(@Valid @ModelAttribute("doctor") Doctor updateDoctor,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "editDoctor";
        }
        doctorService.updateDoctor(updateDoctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    @GetMapping("/restore/{id}")
    public String restoreDoctor(@PathVariable("id") Long id) {
        doctorService.restoreStatus(id);
        return "redirect:/doctors";
    }
}
