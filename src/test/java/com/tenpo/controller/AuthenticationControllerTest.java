package com.tenpo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.dto.LoginDTO;
import com.tenpo.dto.UserDTO;
import com.tenpo.dto.response.UserDataResponse;
import com.tenpo.service.ArithmeticalOperationsService;
import com.tenpo.service.AuthenticationService;
import com.tenpo.service.RequestLoggerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @MockBean
    private AuthenticationService authenticationService;
    @MockBean
    private ArithmeticalOperationsService arithmeticalOperationsService;
    @MockBean
    private RequestLoggerService requestLoggerService;
    @Autowired
    private MockMvc mockMvc;
    private AuthenticationController controller;

    @BeforeEach
    void setUp() {
        controller = new AuthenticationController(authenticationService);
    }

    @Test
    void login() throws Exception {
        String jwt = "some_one_jwt";
        LoginDTO loginDTO = new LoginDTO("pepe", "pepe");
        when(authenticationService.authenticate(any())).thenReturn(jwt);
        String urlTemplate = "/V1/authentication/login";

        mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate)
            .content(new ObjectMapper().writeValueAsString((loginDTO)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void logout() throws Exception {
        String urlTemplate = "/V1/authentication/logout";

        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void singUp() throws Exception {
        String userMock = "pepe";
        String urlTemplate = "/V1/authentication/sign-up";
        UserDTO userDTO = new UserDTO(userMock, userMock, userMock);
        UserDataResponse userDataResponseExpected = UserDataResponse.create(userMock, userMock);
        ResponseEntity<UserDataResponse> userDataResponseExpectedEntity = ResponseEntity.ok(userDataResponseExpected);
        when(authenticationService.userRegistration(userDTO)).thenReturn(userDataResponseExpected);

        ResponseEntity<UserDataResponse> userDataResponse = controller.singUp(userDTO);

        assertEquals(userDataResponseExpectedEntity, userDataResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate)
            .content(new ObjectMapper().writeValueAsString((userDTO)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }
}