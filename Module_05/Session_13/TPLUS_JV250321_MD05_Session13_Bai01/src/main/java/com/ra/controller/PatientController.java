package com.ra.controller;

import com.ra.model.Patient;
import com.ra.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String showAllPatient(Model model) {
        List<Patient> patientList = patientService.getAllPatient();
        model.addAttribute("patients", patientList);
        return "patientList";
    }

    @GetMapping("/add")
    public String showAddPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "addPatient";
        }
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "editPatient";
    }

    @PostMapping("/edit")
    public String editPatient(@Valid @ModelAttribute("patient") Patient updatePatient,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "editPatient";
        }
        patientService.updatePatient(updatePatient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/restore/{id}")
    public String restorePatient(@PathVariable("id") Long id) {
        patientService.restorePatient(id);
        return "redirect:/patients";
    }
}
