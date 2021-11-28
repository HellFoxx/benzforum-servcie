package com.benzforum.repo;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepo extends JpaRepository<Discuss, Long> {

    @Query(value = "select m from Message m where m.discussId = :id")
    List<Message> findMessagesByDiscussId(@Param("id") Long id);

}
