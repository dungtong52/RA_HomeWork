package com.ra.service;

import com.ra.model.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(Message message);

    List<Message> getMessage(Long senderId, Long receiverId);
}
