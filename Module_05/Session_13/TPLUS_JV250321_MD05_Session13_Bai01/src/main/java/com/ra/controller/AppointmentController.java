package com.ra.controller;

import com.ra.dto.AppointmentDto;
import com.ra.model.Appointment;
import com.ra.model.Doctor;
import com.ra.model.Patient;
import com.ra.service.AppointmentService;
import com.ra.service.DoctorService;
import com.ra.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping
    public String listAppointment(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointmentList";
    }

    @GetMapping("/add")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointmentDto", new AppointmentDto());
        model.addAttribute("doctors", doctorService.getDoctorActive());
        model.addAttribute("patients", patientService.getPatientActive());
        return "appointmentForm";
    }

    @PostMapping("/add")
    public String addAppointment(@Valid @ModelAttribute("appointmentDto") AppointmentDto appointmentDto,
                                 BindingResult result,
                                 Model model) {
        System.out.println(1);
        if (result.hasErrors()) {
            return "appointmentForm";
        }

        boolean isBusy = appointmentService.isDoctorBusy(
                appointmentDto.getDoctorId(),
                appointmentDto.getDate(),
                appointmentDto.getHour());
        if (isBusy) {
            model.addAttribute("error", "Bác sĩ đã có lịch tại ngày và giờ này.");
            model.addAttribute("doctors", doctorService.getDoctorActive());
            model.addAttribute("patients", patientService.getPatientActive());
            System.out.println(2);
            return "appointmentForm";
        } else {
            Doctor doctor = doctorService.finfById(appointmentDto.getDoctorId());
            Patient patient = patientService.findById(appointmentDto.getPatientId());

            Appointment appointment = new Appointment();
            appointment.setDate(appointmentDto.getDate());
            appointment.setHour(appointmentDto.getHour());
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);

            appointmentService.save(appointment);
            model.addAttribute("success", "Đặt lịch thành công!");
            model.addAttribute("appointments", appointmentService.getAll());
            System.out.println(3);
            return "appointmentList";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
