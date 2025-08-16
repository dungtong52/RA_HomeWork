package com.ra.repository.imp;

import com.ra.model.User;
import com.ra.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .list();
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().remove(user);
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

}
