package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserService = new GetUserService(userRepository);
    }

    @Test
    void detail() {
        String username = "zzezze";
        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake(username)));

        UserDto userDto = getUserService.detail(username);

        assertThat(userDto.getAmount()).isEqualTo(50000L);
    }
}
