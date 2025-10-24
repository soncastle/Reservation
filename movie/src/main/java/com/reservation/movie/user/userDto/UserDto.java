package com.reservation.movie.user.userDto;

import com.reservation.movie.user.model.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private String userName;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .userName(this.userName)
                .build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}

