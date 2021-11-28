package com.benzforum.service;

import com.benzforum.model.user.User;
import com.benzforum.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public boolean containsNickname(String nickname) {
        String res = userRepo.containsNickname(nickname);
        return res != null;
    }

    public User getUserById(Long id) {
        return userRepo.getById(id);
    }

    public User getUserByNickname(String nickname) {
        return userRepo.getUserByNickname(nickname);
    }
}
