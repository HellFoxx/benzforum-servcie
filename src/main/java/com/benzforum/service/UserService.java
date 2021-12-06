package com.benzforum.service;

import com.benzforum.dto.user.UserEditDto;
import com.benzforum.model.user.User;
import com.benzforum.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        return userRepo.containsNickname(nickname) != null;
    }

    public User getUserById(Long id) {
        User user = userRepo.getById(id);
        return user;
    }

    public User getUserByNickname(String nickname) {
        return userRepo.getUserByNickname(nickname);
    }

    public void updateUser(Long id, UserEditDto userEditDto) {
        userRepo.updateUser(
                id,
                userEditDto.getName(),
                userEditDto.getSurname(),
                userEditDto.getNickname(),
                userEditDto.getEmail()
        );
    }
}
