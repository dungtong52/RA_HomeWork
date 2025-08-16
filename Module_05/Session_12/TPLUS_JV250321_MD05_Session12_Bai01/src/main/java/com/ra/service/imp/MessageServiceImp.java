package com.ra.service.imp;

import com.ra.model.Message;
import com.ra.repository.MessageRepository;
import com.ra.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessage(Long senderId, Long receiverId) {
        return messageRepository.getMessage(senderId, receiverId);
    }
}
