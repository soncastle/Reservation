package com.reservation.movie.user.userDto;

import com.reservation.movie.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequestDto {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    };
}
