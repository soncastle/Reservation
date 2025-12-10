package com.reservation.movie.user.userDto;

import com.reservation.movie.user.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
