package com.tenpo.controller;

import com.tenpo.dto.LoginDTO;
import com.tenpo.dto.UserDTO;
import com.tenpo.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/authentication")
public class AuthenticationController {

    private static final Logger LOG = LogManager.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {

        String jwt = authenticationService.authenticate(loginDTO);

        ResponseCookie cookie = ResponseCookie
            .from("user-id", jwt)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(6000)
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
            .from("user-id", "")
            .path("/")
            .maxAge(0)
            .build();

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();
    }

    @PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void singUp(@RequestBody UserDTO userDTO) {
        authenticationService.userRegistration(userDTO);
    }
}
