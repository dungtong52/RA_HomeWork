package com.ra.service.imp;

import com.ra.model.Appointment;
import com.ra.repository.AppointmentRepo;
import com.ra.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {
    private final AppointmentRepo appointmentRepo;

    public AppointmentServiceImp(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepo.getAll();
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    @Override
    public boolean isDoctorBusy(Long doctorId, LocalDate date, Integer hour) {
        return appointmentRepo.isDoctorBusy(doctorId, date, hour);
    }

    @Override
    public void delete(Long id) {
        appointmentRepo.delete(id);
    }
}
