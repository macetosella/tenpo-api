package com.tenpo.api.service;

import com.tenpo.api.dto.LoginDTO;
import com.tenpo.api.dto.UserDTO;
import com.tenpo.api.exception.InvalidTokenException;
import com.tenpo.api.exception.InvalidUserException;
import com.tenpo.api.exception.InvalidPasswordException;
import com.tenpo.api.model.UserData;
import com.tenpo.api.repository.UserRepository;
import com.tenpo.api.util.DateUtil;
import com.tenpo.api.dto.response.UserDataResponse;
import com.tenpo.api.service.jwt.JWTService;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
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
        Optional<UserData> userData = userRepository.findByUserName(loginDTO.userName);

        userData.ifPresent(user -> {
            if (!Objects.equals(UserData.hashPassword(loginDTO.password), user.password)) {
                throw new InvalidPasswordException("Invalid password");
            }
        });

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

    public UserDataResponse userRegistration(UserDTO userDTO) {
        UserData user = new UserData(userDTO.userName, userDTO.password,
            Date.from(DateUtil.now()));

        UserData userData = userRepository.save(user);

        return UserDataResponse.create(userData.userName);
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
