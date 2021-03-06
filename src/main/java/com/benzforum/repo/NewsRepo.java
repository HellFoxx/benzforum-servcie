package com.benzforum.repo;

import com.benzforum.model.newsitem.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<NewsItem, Long> {

}
