package com.reservation.movie.user.service;

import com.reservation.movie.user.execption.UserException;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.SignupRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserIntegrationTest {
  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;


  @Test
  void signupSuccess(){
    SignupRequestDto dto = new SignupRequestDto();
    dto.setEmail("test@naver.com");
    dto.setPassword("Test123!!");

    String result = userService.createUser(dto);

    assertThat(result).isEqualTo("저장완료");

    User saved = userRepository.findByEmail("test@naver.com").get();
    assertThat(saved).isNotNull();
    assertThat(saved.getEmail()).isEqualTo("test@naver.com");
  }

  @Test
  void signupDuplicateFail(){
    SignupRequestDto dto1 = new SignupRequestDto();
    SignupRequestDto dto2 = new SignupRequestDto();

    dto1.setEmail("test@naver.com");
    dto1.setPassword("Test123!!");

    dto2.setEmail("test@naver.com");
    dto2.setPassword("Test123!!!");

    userService.createUser(dto1);

    assertThatThrownBy(() -> userService.createUser(dto2))
        .isInstanceOf(UserException.class)
        .hasMessage("이미 사용중인 이메일입니다.");
  }

}
