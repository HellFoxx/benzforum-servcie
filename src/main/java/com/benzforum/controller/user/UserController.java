package com.benzforum.controller.user;

import com.benzforum.dto.user.UserDto;
import com.benzforum.model.user.User;
import com.benzforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        if (userService.containsNickname(userDto.getNickname()))
            return new ResponseEntity("Пользователь с таким Никнеймом уже существует", HttpStatus.BAD_REQUEST);
        if (!userDto.getPassword().equals(userDto.getPasswordRepeat()))
            return new ResponseEntity("Пароли не одинаковые", HttpStatus.BAD_REQUEST);
        User user = new User();
        if (userDto.getUserName() != null)
            user.setUserName(userDto.getUserName());
        else
            user.setUserName("");
        if (userDto.getUserSurname() != null)
            user.setUserSurname(userDto.getUserSurname());
        else
            user.setUserSurname("");
        user.setNickname(userDto.getNickname());
        user.setUserPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

}
