package com.benzforum.repo;

import com.benzforum.model.discuss.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepo extends JpaRepository<Discuss, Long> {
}
