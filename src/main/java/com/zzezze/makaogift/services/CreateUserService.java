package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.exceptions.UsernameAlreadyExist;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto create(String name, String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExist();
        }

        User user = new User(name, username, password);

        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user.toDto();
    }
}
