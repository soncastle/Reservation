package com.reservation.movie.user.service;
import com.reservation.movie.execption.ErrorCode;
import com.reservation.movie.user.execption.UserException;
import com.reservation.movie.user.model.CustomUserDetails;
import com.reservation.movie.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public boolean login(String email, String password, HttpServletRequest req) {
        User user = userService.login(email, password);
        if (user == null) throw new UserException(ErrorCode.INVALID_LOGIN);

        CustomUserDetails userDetails = new CustomUserDetails(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        HttpSession session = req.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
        session.setAttribute("user", user);

        return true;
}
}
