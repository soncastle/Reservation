package com.reservation.movie.user.controller;

import com.reservation.movie.user.model.User;
import com.reservation.movie.user.service.UserService;
import com.reservation.movie.user.userDto.LoginRequestDto;
import com.reservation.movie.user.userDto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpSession session) {
        System.out.println(request);
        User user = userService.login(request.getEmail(), request.getPassword());
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        session.setAttribute("user", user);
        return ResponseEntity.ok("로그인!");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료");
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/signup")
    public String createUser(@RequestBody UserDto userdto) {
        return userService.createUser(userdto);
    }


}

