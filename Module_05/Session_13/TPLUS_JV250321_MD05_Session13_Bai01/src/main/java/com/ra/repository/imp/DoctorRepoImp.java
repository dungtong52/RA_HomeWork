package com.ra.repository.imp;

import com.ra.model.Doctor;
import com.ra.repository.DoctorRepo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DoctorRepoImp implements DoctorRepo {
    private final SessionFactory sessionFactory;

    public DoctorRepoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Doctor> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Doctor", Doctor.class)
                .list();
    }

    @Override
    public List<Doctor> findAllActive() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Doctor WHERE status = :status", Doctor.class)
                .setParameter("status", true)
                .list();
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().persist(doctor);
    }

    @Override
    public Doctor finfById(Long id) {
        return sessionFactory.getCurrentSession()
                .find(Doctor.class, id);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().merge(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = finfById(id);
        sessionFactory.getCurrentSession().remove(doctor);
    }

    @Override
    public void restoreStatus(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Doctor d SET d.status = :status WHERE d.id = :id", Doctor.class)
                .setParameter("status", true)
                .setParameter("id", id)
                .executeUpdate();
    }
}
