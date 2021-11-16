package com.benzforum.repo;

import com.benzforum.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select nickname from users u where u.nickname = :nickname")
    String containsNickname(@Param("nick") String nickname);

}
