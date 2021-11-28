package com.benzforum.controller.user;

import com.benzforum.dto.user.UserDto;
import com.benzforum.dto.user.UserSignInDto;
import com.benzforum.model.user.User;
import com.benzforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
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
        user.setUserName(userDto.getUserName() != null ? userDto.getUserName() : "");
        user.setUserSurname(userDto.getUserSurname() != null ? userDto.getUserSurname() : "");
        user.setNickname(userDto.getNickname());
        user.setUserPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(
            value = "/signin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity signIn(@RequestBody UserSignInDto userSignInDto, HttpServletResponse response) {
        User user = userService.getUserByNickname(userSignInDto.getNickname());
        if (user == null)
            return new ResponseEntity("Пользователя с таким никнеймом не существует", HttpStatus.BAD_REQUEST);
        if (!user.getUserPassword().equals(userSignInDto.getPassword()))
            return new ResponseEntity("Пароль введён неверно!", HttpStatus.BAD_REQUEST);
        Cookie cookieId = new Cookie("userId", user.getId().toString());
        cookieId.setPath("/");
        Cookie cookieNickname = new Cookie("userNickname", user.getNickname());
        cookieNickname.setPath("/");
        // if true 6 month else 30 minutes
        int cookieAge = userSignInDto.getIsRemember() ? (6 * 30 * 24 * 3600) : (30 * 60);
        cookieId.setMaxAge(cookieAge);
        cookieNickname.setMaxAge(cookieAge);
        response.addCookie(cookieId);
        response.addCookie(cookieNickname);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity userId(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null)
            return new ResponseEntity("Такого пользователя не существует", HttpStatus.BAD_REQUEST);
        user.setUserPassword("");
        user.setEmail("");
        return ResponseEntity.ok(user);
    }

}
