package com.reservation.movie.user.service;

import com.reservation.movie.execption.ErrorCode;
import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.user.execption.UserException;
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
            throw new UserException(ErrorCode.EMAIL_DUPLICATION);
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
        if (user == null) {
            return null;
        }

        return UserDto.fromEntity(user);
    }

    public List<UserReservationInfoDto> userReservation(String email){
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> reservationList = reservationRepository.findAllByEmailOrderByReservationTimeDesc(email);


        return reservationList.stream()
                .map(UserReservationInfoDto::fromEntity)
                .toList();
    }
}
