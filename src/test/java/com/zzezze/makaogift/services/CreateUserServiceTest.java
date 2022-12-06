package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    private CreateUserService createUserService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(32, 32, 1, 16384, 1);
        createUserService = new CreateUserService(userRepository, passwordEncoder);
    }

    @Test
    void create() {
        String name = "홍길동";
        String username = "zzezze";
        String password = "Password123!";

        UserDto userDto = createUserService.create(name, username, password);

        assertThat(userDto).isNotNull();

        verify(userRepository).save(any());
    }
}
