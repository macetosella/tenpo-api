package com.tenpo.api.controller;

import com.tenpo.api.dto.LoginDTO;
import com.tenpo.api.dto.UserDTO;
import com.tenpo.api.dto.response.UserDataResponse;
import com.tenpo.api.service.AuthenticationService;
import com.tenpo.api.service.jwt.JWTService;
import java.net.URI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @Value("${cookies.maxAgeValue}")
    private Integer maxAgeValue;
    @Value("${cookies.minAgeValue}")
    private Integer minAgeValue;
    @Value("${cookies.path}")
    private String path;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody @Validated LoginDTO loginDTO) {
        LOG.info("[login] Request : {}", loginDTO);
        String jwt = authenticationService.authenticate(loginDTO);

        ResponseCookie cookie = ResponseCookie
            .from(JWTService.JWT_COOKIE_NAME, jwt)
            .httpOnly(true)
            .secure(true)
            .path(path)
            .maxAge(maxAgeValue)
            .build();

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();
    }

    @GetMapping(path = "/logout")
    @ResponseBody
    public ResponseEntity<Object> logout() {
        LOG.info("[logout]");
        ResponseCookie cookie = ResponseCookie
            .from(JWTService.JWT_COOKIE_NAME, "")
            .path(path)
            .maxAge(minAgeValue)
            .build();

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();
    }

    @PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDataResponse> singUp(@RequestBody @Validated UserDTO userDTO) {
        LOG.info("[singUp] Request : {}", userDTO);
        UserDataResponse userDataResponse = authenticationService.userRegistration(userDTO);
        LOG.info("[singUp] Response : {}", userDataResponse);

        return ResponseEntity.created(URI.create("/login")).body(userDataResponse);

    }
}
