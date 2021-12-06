package com.benzforum.controller.user;

import com.benzforum.dto.user.UserDto;
import com.benzforum.dto.user.UserEditDto;
import com.benzforum.dto.user.UserSignInDto;
import com.benzforum.model.user.User;
import com.benzforum.model.user.UserType;
import com.benzforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
        user.setUserType(UserType.SIMPLE);
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
            return new ResponseEntity("Пароль введён неверно", HttpStatus.BAD_REQUEST);
        user.setUserPassword("");
        Cookie cookieId = new Cookie("userId", user.getId().toString());
        cookieId.setPath("/");
        cookieId.setHttpOnly(true);
        // if true 6 month else 30 minutes
        cookieId.setMaxAge(userSignInDto.getIsRemember() ? (6 * 30 * 24 * 3600) : (30 * 60));
        response.addCookie(cookieId);
        return ResponseEntity.ok(user);
    }

    @GetMapping(
            value = "/auth",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity authUser(@CookieValue(value = "userId", defaultValue = "") String userId) {
        if (userId.equals(""))
            return ResponseEntity.ok(null);
        Long id = Long.parseLong(userId);
        User user = userService.getUserById(id);
        if (user == null)
            return new ResponseEntity("NO_USER", HttpStatus.BAD_REQUEST);
        user.setUserPassword("");
        return ResponseEntity.ok(user);
    }

    @GetMapping(
            value = "/reauth",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity reauthUser(
            @CookieValue(value = "userId", defaultValue = "") String userId,
            HttpServletResponse response) {
        if (userId.equals(""))
            return new ResponseEntity("NO_COOKIES", HttpStatus.BAD_REQUEST);
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(
            value = "nickname/{nickname}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity checkNickname(@PathVariable(value = "nickname") String nickname) {
        return ResponseEntity.ok(userService.containsNickname(nickname));
    }

    @PostMapping(
            value = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity editUser(
            @RequestBody UserEditDto userEditDto,
            @CookieValue(value = "userId", defaultValue = "") String userId) {
        userService.updateUser(Long.parseLong(userId), userEditDto);
        return ResponseEntity.ok("ok");
    }

}
