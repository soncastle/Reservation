package com.reservation.movie.user.service;

import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String createUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            return "이미 존재하는 이메일입니다.";
        }

        User user = userDto.toEntity(passwordEncoder); // 비밀번호 암호화
        userRepository.save(user);
        return "저장완료";
    }

    public User login(String email, String password){

        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }

}
