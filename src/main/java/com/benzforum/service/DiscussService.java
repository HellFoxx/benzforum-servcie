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

    public List<Discuss> getAllActiveDiscussions() {
        return discussRepo.findAllActive();
    }

    public void addNewDiscussion(Discuss discuss) {
        discussRepo.save(discuss);
    }

    public List<Discuss> getAllNonActive() {
        return discussRepo.getAllNonActive();
    }

    public void switchToActive(Long id) {
        discussRepo.switchToActive(id);
    }

    public void deleteById(Long id) {
        discussRepo.deleteById(id);
    }
}
