package com.ra.service;

import com.ra.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctor();

    List<Doctor> getDoctorActive();

    void saveDoctor(Doctor doctor);

    Doctor finfById(Long id);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Long id);

    void restoreStatus(Long id);

}
