package com.ra.repository;

import com.ra.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo {
    List<Appointment> getAll();

    void save(Appointment appointment);

    boolean isDoctorBusy(Long doctorId, LocalDate date, Integer hour);

    void delete(Long id);

    Appointment findById(Long id);
}
