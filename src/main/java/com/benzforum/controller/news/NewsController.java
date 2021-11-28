package com.benzforum.controller.news;

import com.benzforum.model.newsitem.NewsItem;
import com.benzforum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNews() {
        List<NewsItem> news = newsService.getAllNews();
        return ResponseEntity.ok(news);
    }

    /*@PostMapping(value = "/add",
                 produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewsItem(@RequestBody NewsItemDto item) {
        long id = news.size();
        item.setId(id);
        news.put(id, item);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteNewsItem(@PathVariable long id) {
        NewsItemDto item = news.get(id);
        if (item == null)
            return new ResponseEntity("Invalid newsItem id!", HttpStatus.BAD_REQUEST);
        news.remove(item);
        return ResponseEntity.ok(null);
    }*/

    /*@PutMapping(value = "/edit/{id}")
    public ResponseEntity editNewsItem(@PathVariable long id, @RequestBody NewsItemDto item) {
        if (item == null || news.get(id) == null)
            return new ResponseEntity("Invalid newsItem id!", HttpStatus.BAD_REQUEST);
        news.put(id, item);
        return ResponseEntity.ok(null);
    }*/

}
