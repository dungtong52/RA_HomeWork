package com.ra.service.imp;

import com.ra.model.Doctor;
import com.ra.repository.DoctorRepo;
import com.ra.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImp implements DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorServiceImp(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepo.findAll();
    }

    @Override
    public List<Doctor> getDoctorActive() {
        return doctorRepo.findAllActive();
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepo.saveDoctor(doctor);
    }

    @Override
    public Doctor finfById(Long id) {
        return doctorRepo.finfById(id);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorRepo.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepo.deleteDoctor(id);
    }

    @Override
    public void restoreStatus(Long id) {
        doctorRepo.restoreStatus(id);
    }
}
