package com.tenpo.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.tenpo.api.dto.LoginDTO;
import com.tenpo.api.dto.UserDTO;
import com.tenpo.api.exception.InvalidTokenException;
import com.tenpo.api.exception.InvalidUserException;
import com.tenpo.api.model.UserData;
import com.tenpo.api.repository.UserRepository;
import com.tenpo.api.service.jwt.JWTService;
import com.tenpo.api.dto.response.UserDataResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.Cookie;
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
    void authenticate() throws ExecutionException {
        String mockUser = "pepe";
        String jwtExpected = "jwt.jwt.jwt";
        LoginDTO loginDTO = new LoginDTO(mockUser, mockUser);
        UserData userData = new UserData(mockUser, mockUser, new Date());
        Optional<UserData> optionalUserData = Optional.of(userData);
        when(userRepository.findByUserName(mockUser)).thenReturn(optionalUserData);
        when(jwtService.buildToken(any(), any())).thenReturn(jwtExpected);

        String jwt = authenticationService.authenticate(loginDTO);

        assertEquals(jwtExpected, jwt);
    }

    @Test
    void authenticate_user_not_signup() {
        String mockUser = "pepe";
        LoginDTO loginDTO = new LoginDTO(mockUser, mockUser);
        when(userRepository.findByUserName(mockUser)).thenReturn(Optional.empty());

        Executable ex = () -> authenticationService.authenticate(loginDTO);

        assertThrows(InvalidUserException.class, ex);
    }

    @Test
    void userRegistration() {
        String mockUser = "pepe";
        UserDTO userDTO = new UserDTO(mockUser, mockUser);
        UserData userData = new UserData(mockUser, mockUser, new Date());
        when(userRepository.save(any())).thenReturn(userData);
        UserDataResponse userDataResponseExpected = UserDataResponse.create(mockUser);

        UserDataResponse userDataResponse = authenticationService.userRegistration(userDTO);

        assertEquals(userDataResponseExpected.userName, userDataResponse.userName);
        assertEquals(userDataResponseExpected.userName, userDataResponse.userName);
    }

    @Test
    void validateAuth() {
        String cookieValueMock = "mockValue";
        Cookie cookie = new Cookie(JWTService.JWT_COOKIE_NAME, cookieValueMock);
        Cookie[] cookiesMock = {cookie};
        doNothing().when(jwtService).verifyToken(any());

        Executable ex = () -> authenticationService.validateAuth(cookiesMock);

        assertDoesNotThrow(ex);
    }

    @Test
    void validateAuth_session_not_found() {
        String cookieValueMock = "mockValue";
        Cookie cookie = new Cookie("bad_session", cookieValueMock);
        Cookie[] cookiesMock = {cookie};

        Executable ex = () -> authenticationService.validateAuth(cookiesMock);

        assertThrows(InvalidTokenException.class, ex);
    }

    @Test
    void validateAuth_cookie_not_found() {
        Cookie[] cookiesMock = {};

        Executable ex = () -> authenticationService.validateAuth(cookiesMock);

        assertThrows(InvalidTokenException.class, ex);
    }
}