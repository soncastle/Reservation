package com.reservation.movie.user.service;
import com.reservation.movie.execption.ErrorCode;
import com.reservation.movie.user.execption.UserException;
import com.reservation.movie.user.model.CustomUserDetails;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository;

    public String login(String email, String password, HttpServletRequest req, HttpServletResponse res) {

        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UserException(ErrorCode.INVALID_LOGIN));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UserException(ErrorCode.INVALID_LOGIN);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);

        securityContextRepository.saveContext(context, req, res);
        SecurityContextHolder.setContext(context);

        return email;
    }

    public UserDto checkUserSession(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            throw new UserException(ErrorCode.AUTH_REQUEST);
        }
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return UserDto.fromEntity(userDetails.getUser());
    }
}
