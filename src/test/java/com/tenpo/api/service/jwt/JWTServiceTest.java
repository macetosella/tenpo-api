package com.tenpo.api.service.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tenpo.api.exception.InvalidTokenException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class JWTServiceTest {

    @Test
    void buildToken() {
        JWTService jwtService = new JWTService();

        Map<String, Object> claims = Map.of(
            "sub", "pepe",
            "exp", "1638873186238"
        );

        String jwt = jwtService.buildToken(claims, null);

        assertNotNull(jwt);
    }

    @Test
    void verifyToken_invalid_token() {
        String badJwtMock = "balbalbal.blablabla.blabla";
        JWTService jwtService = new JWTService();

        Executable ex = () -> jwtService.verifyToken(badJwtMock);

        assertThrows(InvalidTokenException.class, ex);
    }
}