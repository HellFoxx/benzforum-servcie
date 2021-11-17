package com.benzforum.controller.forum;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.user.User;
import com.benzforum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/forum")
@CrossOrigin("*")
public class ForumController {

    private final ForumService forumService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscussions() {
        List<Discuss> discussions = forumService.getAllDiscussions();
        for (Discuss item : discussions) {
            User user = item.getAuthor();
            user.setUserPassword("");
            user.setEmail("");
            item.setAuthor(user);
        }
        return ResponseEntity.ok(discussions);
    }

}
