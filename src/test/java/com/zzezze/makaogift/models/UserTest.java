package com.zzezze.makaogift.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        passwordEncoder = new Argon2PasswordEncoder(32, 32, 1, 16384, 1);
    }

    @Test
    void authenticate() {
        User user = User.fake("1234");

        user.changePassword("Password123!", passwordEncoder);

        assertThat(user.authenticate("Password123!", passwordEncoder)).isTrue();
        assertThat(user.authenticate("Passwordasdb", passwordEncoder)).isFalse();
    }

    @Test
    void order() {
        User user = User.fake("1234");

        user.order(10000L);

        assertThat(user.amount()).isEqualTo(40000L);
    }
}
