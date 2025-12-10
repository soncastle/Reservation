package com.reservation.movie.user.service;

import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.SignupRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Test
  void signupSuccess() {
    SignupRequestDto signupRequestDto = new SignupRequestDto();
     signupRequestDto.setEmail("test@naver.com");
     signupRequestDto.setPassword("Test123!!");

    when(userRepository.existsByEmail("test@naver.com"))
        .thenReturn(false);

    when(passwordEncoder.encode(signupRequestDto.getPassword()))
        .thenReturn("encodedPassword");

    String result = userService.createUser(signupRequestDto);

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(userRepository, times(1)).save(captor.capture());

    User saveUser = captor.getValue();
    assertThat(saveUser.getPassword()).isEqualTo("encodedPassword");

    assertThat(result).isEqualTo("저장완료");

  }


}
