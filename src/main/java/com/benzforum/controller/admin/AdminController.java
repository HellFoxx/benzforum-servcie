package com.benzforum.controller.admin;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.newsitem.NewsItem;
import com.benzforum.service.DiscussService;
import com.benzforum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class AdminController {

    private final DiscussService discussService;
    private final NewsService newsService;

    @Autowired
    public AdminController(DiscussService discussService, NewsService newsService) {
        this.discussService = discussService;
        this.newsService = newsService;
    }

    @GetMapping(
            value = "/discussions/non-active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getNonActiveDiscussions() {
        List<Discuss> NonActiveDiscussions = discussService.getAllNonActive();
        for (Discuss discuss : NonActiveDiscussions) {
            discuss.getAuthor().setUserPassword("");
            discuss.getAuthor().setEmail("");
        }
        return ResponseEntity.ok(NonActiveDiscussions);
    }

    @PutMapping(
            value = "/discussions/non-active/accept/{id}"
    )
    public HttpStatus acceptDiscuss(@PathVariable("id") Long id) {
        discussService.switchToActive(id);
        return HttpStatus.OK;
    }

    @DeleteMapping(
            value = "/discussions/non-active/delete/{id}"
    )
    public HttpStatus deleteNonActiveDiscuss(@PathVariable("id") Long id) {
        discussService.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(
            value = "/news/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpStatus addNewsItem(@RequestBody NewsItem newsItem) {
        newsItem.setPublicDate(new Date());
        newsItem.setAuthorId(1L);
        newsService.save(newsItem);
        return HttpStatus.OK;
    }

}
