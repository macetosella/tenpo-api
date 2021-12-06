package com.tenpo.service;

import com.tenpo.exception.InvalidTokenException;
import com.tenpo.exception.InvalidUserException;
import com.tenpo.util.DateUtil;
import com.tenpo.dto.LoginDTO;
import com.tenpo.dto.UserDTO;
import com.tenpo.model.UserData;
import com.tenpo.repository.UserRepository;
import com.tenpo.service.jwt.JWTService;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;

    public AuthenticationService(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public String authenticate(LoginDTO loginDTO) {
        Optional<UserData> userData = userRepository.findByUserNickNameAndPassword(loginDTO.userName,
            loginDTO.password);
        Date expirationDate = Date.from(DateUtil.now().plus(JWTService.EXPIRATION_TIME));

        return userData.map(user -> {
                Map<String, Object> claims = Map.of(
                    "sub", user.userName,
                    "exp", expirationDate
                );
                return jwtService.buildToken(claims, null);
            }
        ).orElseThrow(() -> new InvalidUserException("User not found"));
    }

    public void userRegistration(UserDTO userDTO) {
        UserData user = new UserData(userDTO.userName, userDTO.userNickName, userDTO.password,
            Date.from(DateUtil.now()));
        userRepository.save(user);
    }

    public void validateAuth(Cookie[] cookies) {
        if (cookies != null) {
            Cookie session = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(JWTService.JWT_COOKIE_NAME))
                .findFirst().orElseThrow(() -> new InvalidTokenException("Session not found"));

            if (session != null) {
                jwtService.verifyToken(session.getValue());
            }
        } else {
            throw new InvalidTokenException("Cookie not found");
        }
    }
}
