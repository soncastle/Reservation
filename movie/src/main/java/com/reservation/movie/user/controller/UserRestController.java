package com.reservation.movie.user.controller;

import com.reservation.movie.user.model.CustomUserDetails;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.service.UserService;
import com.reservation.movie.user.userDto.LoginRequestDto;
import com.reservation.movie.user.userDto.UserDto;
import com.reservation.movie.user.userDto.UserReservationInfoDto;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpServletRequest req) {
        User user = userService.login(request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        // ✅ 인증 객체 생성
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        // ✅ SecurityContext 생성 및 등록
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        // ✅ 세션에 SPRING_SECURITY_CONTEXT 등록
        HttpSession session = req.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
        session.setAttribute("user", user);

        return ResponseEntity.ok("로그인 성공");
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

    @GetMapping("/my-reservation")
    public ResponseEntity<?> userMyReservation(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("❌ 세션에 user 없음");
            return null;
        }
        String email = user.getEmail();
        List<UserReservationInfoDto> result = userService.userMyReservation(email);

        if (result.isEmpty()) {
            return ResponseEntity.ok("예약 내역이 없습니다.");
        }
        return ResponseEntity.ok(result);
    }
}
