package com.ra.repository.imp;

import com.ra.model.Appointment;
import com.ra.repository.AppointmentRepo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class AppointmentRepoImp implements AppointmentRepo {
    private final SessionFactory sessionFactory;

    public AppointmentRepoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Appointment> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Appointment", Appointment.class)
                .list();
    }

    @Override
    public void save(Appointment appointment) {
        sessionFactory.getCurrentSession().persist(appointment);
    }

    @Override
    public boolean isDoctorBusy(Long doctorId, LocalDate date, Integer hour) {
        String hql = "SELECT COUNT(a.id) FROM Appointment a " +
                "WHERE a.doctor.id = :doctorId AND a.date = :date AND a.hour = :hour";
        Long count = sessionFactory.getCurrentSession()
                .createQuery(hql, Long.class)
                .setParameter("doctorId", doctorId)
                .setParameter("date", date)
                .setParameter("hour", hour)
                .uniqueResult();
        return count != null && count > 0;
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = findById(id);
        sessionFactory.getCurrentSession().remove(appointment);
    }

    @Override
    public Appointment findById(Long id) {
        return sessionFactory.getCurrentSession().find(Appointment.class, id);
    }
}
