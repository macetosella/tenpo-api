package com.tenpo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tenpo.dto.LoginDTO;
import com.tenpo.dto.UserDTO;
import com.tenpo.dto.response.UserDataResponse;
import com.tenpo.exception.InvalidUserException;
import com.tenpo.model.UserData;
import com.tenpo.repository.UserRepository;
import com.tenpo.service.jwt.JWTService;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private JWTService jwtService;
    @Mock
    private UserRepository userRepository;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(jwtService, userRepository);
    }

    @Test
    void authenticate() {
        String mockUser = "pepe";
        String jwtExpected = "jwt.jwt.jwt";
        LoginDTO loginDTO = new LoginDTO(mockUser, mockUser);
        UserData userData = new UserData(mockUser, mockUser, mockUser, new Date());
        Optional<UserData> optionalUserData = Optional.of(userData);
        when(userRepository.findByUserNickNameAndPassword(mockUser, mockUser)).thenReturn(optionalUserData);
        when(jwtService.buildToken(any(), any())).thenReturn(jwtExpected);

        String jwt = authenticationService.authenticate(loginDTO);

        assertEquals(jwtExpected, jwt);
    }

    @Test
    void authenticate_user_not_signup() {
        String mockUser = "pepe";
        LoginDTO loginDTO = new LoginDTO(mockUser, mockUser);
        when(userRepository.findByUserNickNameAndPassword(mockUser, mockUser)).thenReturn(Optional.empty());

        Executable ex = () -> authenticationService.authenticate(loginDTO);

        assertThrows(InvalidUserException.class, ex);
    }

    @Test
    void userRegistration() {
        String mockUser = "pepe";
        UserDTO userDTO = new UserDTO(mockUser, mockUser, mockUser);
        UserData userData = new UserData(mockUser, mockUser, mockUser, new Date());
        when(userRepository.save(any())).thenReturn(userData);
        UserDataResponse userDataResponseExpected = UserDataResponse.create(mockUser, mockUser);

        UserDataResponse userDataResponse = authenticationService.userRegistration(userDTO);

        assertEquals(userDataResponseExpected.userName, userDataResponse.userName);
        assertEquals(userDataResponseExpected.userNickName, userDataResponse.userNickName);
    }

    @Test
    void validateAuth() {
    }

    @Test
    void saveToken() {
    }
}