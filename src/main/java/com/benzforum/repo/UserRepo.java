package com.benzforum.repo;

import com.benzforum.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select nickname from users where nickname = :nickname", nativeQuery = true)
    String containsNickname(String nickname);

    @Query(value = "select u from User u where u.nickname = :nickname")
    User getUserByNickname(String nickname);
}
