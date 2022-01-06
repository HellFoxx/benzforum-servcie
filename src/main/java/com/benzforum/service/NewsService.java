package com.benzforum.service;

import com.benzforum.model.newsitem.NewsItem;
import com.benzforum.repo.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepo newsRepo;

    @Autowired
    public NewsService(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    public List<NewsItem> getAllNews() {
        return newsRepo.findAll();
    }

    public void save(NewsItem newsItem) {
        newsRepo.save(newsItem);
    }
}
