package com.reservation.movie.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // ✅ 권한 반환 (ROLE_USER, ROLE_ADMIN 등)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole())); // ex: ROLE_USER
    }

    // ✅ 비밀번호 반환
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // ✅ 아이디(email 등) 반환
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // ✅ 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ✅ 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // ✅ 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // ✅ 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    // ✅ User 객체 직접 접근 가능
    public User getUser() {
        return user;
    }
}
