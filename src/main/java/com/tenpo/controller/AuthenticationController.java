package com.tenpo.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/authentication")
public class AuthenticationController {


    public static final String SECRET_KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final Long EXPIRATION_TIME = 1000L * 60 * 30;

    private static final Logger LOG = LogManager.getLogger(AuthenticationController.class);

    @GetMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<Object> login() {

        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        String jwt = Jwts.builder().setSubject("Joe").signWith(signatureAlgorithm, signingKey).setExpiration(exp)
            .compact();

        ResponseCookie cookie = ResponseCookie
            .from("user-id", jwt)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(60000)
            .build();

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();

    }

    @GetMapping(path = "/logout")
    @ResponseBody
    public ResponseEntity<Object> logout() {
        ResponseCookie cookie = ResponseCookie
            .from("user-id", null)
            .path("/")
            .maxAge(0)
            .build();

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();
    }
}
