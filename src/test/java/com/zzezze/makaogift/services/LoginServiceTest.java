package com.zzezze.makaogift.services;

import com.zzezze.makaogift.exceptions.LoginFailed;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(32, 32, 1, 16384, 1);
        loginService = new LoginService(userRepository, passwordEncoder);

        User user = User.fake("1234");
        user.changePassword("Password123!", passwordEncoder);

        given(userRepository.findByUsername("1234"))
                .willReturn(Optional.of(user));
    }

    @Test
    void loginSuccess() {
        User found = loginService.login("1234","Password123!");

        assertThat(found.username()).isEqualTo("1234");
    }

    @Test
    void loginWithWrongPassword() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("1234","xxx!");
        });
    }

    @Test
    void loginWithWrongUsername() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("xxx","Password123!");
        });
    }
}
