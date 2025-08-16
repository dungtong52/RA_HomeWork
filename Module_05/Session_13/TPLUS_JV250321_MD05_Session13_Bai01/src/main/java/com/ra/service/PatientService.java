package com.ra.service;

import com.ra.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatient();

    List<Patient> getPatientActive();

    void savePatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatient(Long id);

    Patient findById(Long id);

    void restorePatient(Long id);
}
