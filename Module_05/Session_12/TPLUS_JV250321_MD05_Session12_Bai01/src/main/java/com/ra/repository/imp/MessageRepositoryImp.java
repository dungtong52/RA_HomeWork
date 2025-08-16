package com.ra.repository.imp;

import com.ra.model.Message;
import com.ra.repository.MessageRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageRepositoryImp implements MessageRepository {
    private final SessionFactory sessionFactory;

    public MessageRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Message message) {
        sessionFactory.getCurrentSession().persist(message);
    }

    @Override
    public List<Message> getMessage(Long senderId, Long receiverId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select m from Message m " +
                        "where (m.sender.id = :senderId and m.receiver.id = :receiverId) " +
                        "or (m.sender.id = :receiverId and m.receiver.id = :senderId) " +
                        "order by m.sentDate asc", Message.class)
                .setParameter("senderId", senderId)
                .setParameter("receiverId", receiverId)
                .list();
    }
}
