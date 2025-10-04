package com.reservation.movie.user.service;

import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String createUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            return "이미 존재하는 이메일입니다.";
        };
        User createUser = userDto.toEntity();
        userRepository.save(createUser);
        return "저장완료";
    }

}
