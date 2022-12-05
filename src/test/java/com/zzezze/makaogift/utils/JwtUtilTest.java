package com.zzezze.makaogift.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
    static final String SECRET = "SECRET";
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil("secret");
    }
    @Test
    void encodeAndDecode() {
        String token = jwtUtil.encode("username");

        String username = jwtUtil.decode(token);

        assertThat(username).isEqualTo("username");
    }
}
