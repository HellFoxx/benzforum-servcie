package com.benzforum.repo;

import com.benzforum.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.nickname = :nickname")
    String containsNickname(String nickname);

    @Query(value = "select u from User u where u.nickname = :nickname")
    User getUserByNickname(String nickname);

    @Transactional
    @Modifying
    @Query(value =
            "update User u set " +
            "u.userName = :name," +
            "u.userSurname = :surname," +
            "u.nickname = :nickname," +
            "u.email = :email " +
            "where u.id = :id")
    void updateUser(Long id, String name, String surname, String nickname, String email);

}
