package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.exceptions.UserNotFound;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {
    private UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto detail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        return user.toDto();
    }
}
