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

    @GetMapping("/checkSession")
    public ResponseEntity<?> checkUserSession(HttpSession session) {
        UserDto userDto = userService.checkUserSession(session);

        // ✅ 세션이 없거나 로그인 안 되어 있으면 401
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("세션이 만료되었거나 로그인 상태가 아닙니다.");
        }

        // ✅ 세션이 유효하면 사용자 정보 반환
        return ResponseEntity.ok(userDto);
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
