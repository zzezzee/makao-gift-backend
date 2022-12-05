package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.exceptions.LoginFailed;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void loginSuccess() throws Exception {
        given(loginService.login("zzezze", "Password123!"))
                .willReturn(User.fake("zzezze"));

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"username\":\"zzezze\", " +
                                "\"password\":\"Password123!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"amount\"")
                ));
    }

    @Test
    void loginWithWrongPassword() throws Exception {
        given(loginService.login("zzezze", "xxx!"))
                .willThrow(new LoginFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"username\":\"zzezze\", " +
                                "\"password\":\"xxx!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
