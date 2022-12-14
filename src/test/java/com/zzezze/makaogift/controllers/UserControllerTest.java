package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import com.zzezze.makaogift.services.CreateUserService;
import com.zzezze.makaogift.services.GetUserService;
import com.zzezze.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @SpyBean
    private CreateUserService createUserService;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void user() throws Exception {
        String username = "zzezze";

        given(getUserService.detail(username))
                .willReturn(User.fake(username).toDto());

        String token = jwtUtil.encode(username);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"amount\"")
                ));
    }

    @Test
    void validRegister() throws Exception {
//        User user = User.fake("12345678");
//
//        given(createUserService.create(any(), any(), any()))
//                .willReturn(user.toDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"?????????\"," +
                                "\"username\":\"zzezze\"," +
                                "\"password\":\"Password123!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("amount")
                ));
    }

    @Test
    void registerWithEnglishName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"english\"," +
                                "\"username\":\"zzezze\"," +
                                "\"password\":\"Password123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerWithBlank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"\"," +
                                "\"username\":\"\"," +
                                "\"password\":\"\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerWithWrongPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"?????????\"," +
                                "\"username\":\"user2\"," +
                                "\"password\":\"Password123\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
