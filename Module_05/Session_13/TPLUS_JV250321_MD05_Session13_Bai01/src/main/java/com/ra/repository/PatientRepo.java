package com.ra.repository;

import com.ra.model.Patient;

import java.util.List;

public interface PatientRepo {
    List<Patient> getAll();

    List<Patient> getAllActive();

    void save(Patient patient);

    void update(Patient patient);

    void delete(Long id);

    Patient findById(Long id);

    void restore(Long id);
}
