package com.ra.repository.imp;

import com.ra.model.Patient;
import com.ra.repository.PatientRepo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PatientRepoImp implements PatientRepo {
    private final SessionFactory sessionFactory;

    public PatientRepoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Patient> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Patient", Patient.class)
                .list();
    }

    @Override
    public List<Patient> getAllActive() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Patient where status = :status", Patient.class)
                .setParameter("status", true)
                .list();
    }

    @Override
    public void save(Patient patient) {
        sessionFactory.getCurrentSession().persist(patient);
    }

    @Override
    public void update(Patient patient) {
        sessionFactory.getCurrentSession().merge(patient);
    }

    @Override
    public void delete(Long id) {
        Patient patient = findById(id);
        sessionFactory.getCurrentSession().remove(patient);
    }

    @Override
    public Patient findById(Long id) {
        return sessionFactory.getCurrentSession().find(Patient.class, id);
    }

    @Override
    public void restore(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Patient p SET p.status = :status WHERE p.id = :id", Patient.class)
                .setParameter("status", true)
                .setParameter("id", id)
                .executeUpdate();
    }
}
