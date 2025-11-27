package com.reservation.movie.user.controller;

import com.reservation.movie.common.ApiResponse;
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
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDto request, HttpServletRequest req, HttpServletResponse res) {
        String email = authService.login(request.getEmail(), request.getPassword(), req, res);
        return ResponseEntity.ok(ApiResponse.success(email));
    }

    @GetMapping("/checkSession")
    public ResponseEntity<ApiResponse<UserDto>> checkUserSession() {
        UserDto userDto = authService.checkUserSession();
        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.success("로그아웃되었습니다."));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody UserDto userdto) {
        String response = userService.createUser(userdto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/reservations")
    public ResponseEntity<ApiResponse<List<UserReservationInfoDto>>> userReservation() {
        UserDto userInfo = authService.checkUserSession();
        String userEmail = userInfo.getEmail();
        List<UserReservationInfoDto> response = userService.userReservation(userEmail);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}