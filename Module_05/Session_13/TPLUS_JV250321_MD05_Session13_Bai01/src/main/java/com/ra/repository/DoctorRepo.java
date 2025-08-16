package com.ra.repository;

import com.ra.model.Doctor;

import java.util.List;

public interface DoctorRepo {
    List<Doctor> findAll();

    List<Doctor> findAllActive();

    void saveDoctor(Doctor doctor);

    Doctor finfById(Long id);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Long id);

    void restoreStatus(Long id);
}
