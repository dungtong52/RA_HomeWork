package com.ra.repository;

import com.ra.model.Message;

import java.util.List;

public interface MessageRepository {
    void save(Message message);

    List<Message> getMessage(Long senderId, Long receiverId);
}
