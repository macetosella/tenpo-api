package com.tenpo.service;

import com.tenpo.dto.LoginDTO;
import com.tenpo.dto.UserDTO;
import com.tenpo.model.UserData;
import com.tenpo.repository.UserRepository;
import com.tenpo.service.jwt.JWTService;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public static final Long EXPIRATION_TIME = 1000L * 60 * 30;

    private final JWTService jwtService;
    private final UserRepository userRepository;

    public AuthenticationService(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public String authenticate(LoginDTO loginDTO) {
        Optional<UserData> userData = userRepository.findByUserNickNameAndPassword(loginDTO.userName,
            loginDTO.password);

        return userData.map(user -> {
                Map<String, Object> claims = Map.of(
                    "sub", user.userName,
                    "exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                );
                return jwtService.buildToken(claims, null);
            }
        ).orElse(null);
    }

    public void userRegistration(UserDTO userDTO) {
        UserData user = new UserData(userDTO.userName, userDTO.userNickName, userDTO.password, new Date());
        userRepository.save(user);
    }
}
