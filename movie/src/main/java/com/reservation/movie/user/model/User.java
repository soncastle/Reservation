package com.reservation.movie.user.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "role", nullable = false, length = 5)
    private String role;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name")
    private String userName;

}
