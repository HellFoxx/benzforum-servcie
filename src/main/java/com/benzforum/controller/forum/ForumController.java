package com.benzforum.controller.forum;

import com.benzforum.dto.message.MessageDto;
import com.benzforum.model.discuss.Discuss;
import com.benzforum.model.message.Message;
import com.benzforum.model.user.User;
import com.benzforum.repo.UserRepo;
import com.benzforum.service.DiscussService;
import com.benzforum.service.MessageService;
import com.benzforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class ForumController {

    private final DiscussService discussService;
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public ForumController(DiscussService forumService, MessageService messageService, UserService userService) {
        this.discussService = forumService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscussions() {
        List<Discuss> discussions = discussService.getAllDiscussions();
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
        List<Message> messageList = messageService.findMessagesByDiscussId(id);
        if (messageList == null)
            return new ResponseEntity("Error!", HttpStatus.BAD_REQUEST);
        for (Message message : messageList) {
            message.getAuthor().setUserPassword("");
            message.getAuthor().setEmail("");
        }
        return ResponseEntity.ok(messageList);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendMessage(@PathVariable("id") Long discussId, @RequestBody MessageDto messageDto) {
        User author = new User();
        Long authorId = messageDto.getAuthorId();
        author.setId(authorId);
        Message message = new Message();
        message.setPublicDate(new Date());
        message.setDiscussId(discussId);
        message.setAuthor(author);
        message.setMsgText(messageDto.getMessageText());
        messageService.addMessage(message);
        author = userService.getUserById(authorId);
        author.setEmail("");
        author.setUserPassword("");
        message.setAuthor(author);
        return new ResponseEntity(message, HttpStatus.OK);
    }

}
