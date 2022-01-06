package com.benzforum.repo;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiscussRepo extends JpaRepository<Discuss, Long> {

    @Query(value = "select d from Discuss d where d.status = 'ACTIVE'")
    List<Discuss> findAllActive();

    @Query(value = "select d from Discuss d where d.status = 'NON_ACTIVE'")
    List<Discuss> getAllNonActive();

    @Transactional
    @Modifying
    @Query(value = "update Discuss d set d.status = 'ACTIVE' where d.id = :id")
    void switchToActive(Long id);
}
