package com.ra.service.imp;

import com.ra.model.Patient;
import com.ra.repository.PatientRepo;
import com.ra.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {
    private final PatientRepo patientRepo;

    public PatientServiceImp(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepo.getAll();
    }

    @Override
    public List<Patient> getPatientActive() {
        return patientRepo.getAllActive();
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepo.update(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepo.delete(id);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepo.findById(id);
    }

    @Override
    public void restorePatient(Long id) {
        patientRepo.restore(id);
    }
}
