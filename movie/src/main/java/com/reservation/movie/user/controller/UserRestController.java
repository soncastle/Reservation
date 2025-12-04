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
import com.reservation.movie.user.userDto.SignupRequestDto;
import com.reservation.movie.user.userDto.UserDto;
import com.reservation.movie.user.userDto.UserReservationInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.util.List;

@Tag(name = "User API", description = "사용자 정보")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDto request, HttpServletRequest req, HttpServletResponse res) {
        String email = authService.login(request.getEmail(), request.getPassword(), req, res);
        return ResponseEntity.ok(ApiResponse.success(email));
    }
    
    @Operation(summary = "세션확인")
    @GetMapping("/checkSession")
    public ResponseEntity<ApiResponse<UserDto>> checkUserSession() {
        UserDto userDto = authService.checkUserSession();
        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.success("로그아웃되었습니다."));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        String response = userService.createUser(signupRequestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @Operation(summary = "예약현황", description = "사용자별 예약한 좌석 리스트")
    @GetMapping("/reservations")
    public ResponseEntity<ApiResponse<List<UserReservationInfoDto>>> userReservation() {
        UserDto userInfo = authService.checkUserSession();
        String userEmail = userInfo.getEmail();
        List<UserReservationInfoDto> response = userService.userReservation(userEmail);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}