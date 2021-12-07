package com.tenpo.service.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tenpo.exception.InvalidTokenException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class JWTServiceTest {

    @Test
    void buildToken() {
        String jwtMock = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMiIsImV4cCI6IjE2Mzg4NzMxODYyMzgifQ";
        JWTService jwtService = new JWTService();

        Map<String, Object> claims = Map.of(
            "sub", "hola2",
            "exp", "1638873186238"
        );

        String jwt = jwtService.buildToken(claims, null);
        String[] chunks = jwt.split("\\.");
        jwt = chunks[0] + "." + chunks[1];

        assertEquals(jwtMock, jwt);
    }

    @Test
    void verifyToken_invalid_token() {
        String badJwtMock = "balbalbal.blablabla.blabla";
        JWTService jwtService = new JWTService();

        Executable ex = () -> jwtService.verifyToken(badJwtMock);

        assertThrows(InvalidTokenException.class, ex);
    }
}