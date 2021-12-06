package com.benzforum.service;

import com.benzforum.model.message.Message;
import com.benzforum.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> findMessagesByDiscussId(Long id) {
        List<Message> list = messageRepo.findMessagesByDiscussId(id);
        return list;
    }

    public Message addMessage(Message message) {
        return messageRepo.save(message);
    }
}
