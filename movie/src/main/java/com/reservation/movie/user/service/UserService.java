package com.reservation.movie.user.service;

import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.UserDto;
import com.reservation.movie.user.userDto.UserReservationInfoDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationRepository reservationRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String createUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            return "이미 존재하는 이메일입니다.";
        }

        User user = userDto.toEntity(passwordEncoder); // 비밀번호 암호화
        user.setRole("user");
        userRepository.save(user);
        return "저장완료";
    }

    public User login(String email, String password){
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }

    public UserDto checkUserSession(HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println("유저!" + user);
        // 2️⃣ 세션이 비어있으면 null 반환
        if (user == null) {
            return null;
        }

        // 3️⃣ UserDto로 변환해서 반환
        return UserDto.fromEntity(user);
    }

    public List<UserReservationInfoDto> userMyReservation(String email){
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> reservationList = reservationRepository
                .findAllByEmailAndReservationTimeAfterOrderByReservationTimeAsc(email, now);

        // 엔티티 → DTO 변환
        return reservationList.stream()
                .map(UserReservationInfoDto::fromEntity)
                .toList();
    }
}
