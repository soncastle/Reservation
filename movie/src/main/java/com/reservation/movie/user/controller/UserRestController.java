package com.reservation.movie.user.controller;

import com.reservation.movie.execption.ErrorCode;
import com.reservation.movie.reservation.execption.ReservationException;
import com.reservation.movie.user.execption.UserException;
import com.reservation.movie.user.model.CustomUserDetails;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.service.AuthService;
import com.reservation.movie.user.service.UserService;
import com.reservation.movie.user.userDto.LoginRequestDto;
import com.reservation.movie.user.userDto.UserDto;
import com.reservation.movie.user.userDto.UserReservationInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpServletRequest req, HttpServletResponse res) {
        String email = authService.login(request.getEmail(), request.getPassword(), req, res);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/checkSession")
    public ResponseEntity<?> checkUserSession() {
        UserDto userDto = authService.checkUserSession();
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

    @GetMapping("/reservations")
    public ResponseEntity<?> userReservation() {
        UserDto userInfo = authService.checkUserSession();

        String userEmail = userInfo.getEmail();
        List<UserReservationInfoDto> result = userService.userReservation(userEmail);

        if (result.isEmpty()) {
           throw new UserException(ErrorCode.RESERVATION_NOT_FOUND);
        }
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}