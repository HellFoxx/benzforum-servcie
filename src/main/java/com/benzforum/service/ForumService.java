package com.benzforum.service;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import com.benzforum.repo.ForumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    private final ForumRepo forumRepo;

    @Autowired
    public ForumService(ForumRepo forumRepo) {
        this.forumRepo = forumRepo;
    }

    public List<Discuss> getAllDiscussions() {
        return forumRepo.findAll();
    }

    public List<Message> findMessagesByDiscussId(Long id) {
        List<Message> list = forumRepo.findMessagesByDiscussId(id);
        return list;
    }
}
