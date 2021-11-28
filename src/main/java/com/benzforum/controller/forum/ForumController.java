package com.benzforum.controller.forum;

import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import com.benzforum.model.user.User;
import com.benzforum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
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
            item.getAuthor().setUserPassword("");
            item.getAuthor().setEmail("");
        }
        return ResponseEntity.ok(discussions);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getDiscussById(@PathVariable("id") Long id ) {
        List<Message> messageList = forumService.findMessagesByDiscussId(id);
        if (messageList == null)
            return new ResponseEntity("Error!", HttpStatus.BAD_REQUEST);
        for (Message message : messageList) {
            message.getAuthor().setUserPassword("");
            message.getAuthor().setEmail("");
        }
        return ResponseEntity.ok(messageList);
    }

}
