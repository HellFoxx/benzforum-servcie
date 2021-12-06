package com.benzforum.service;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import com.benzforum.repo.DiscussRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussService {

    private final DiscussRepo discussRepo;

    @Autowired
    public DiscussService(DiscussRepo forumRepo) {
        this.discussRepo = forumRepo;
    }

    public List<Discuss> getAllDiscussions() {
        return discussRepo.findAll();
    }

}
