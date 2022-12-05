package com.zzezze.makaogift.services;

import com.zzezze.makaogift.exceptions.LoginFailed;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(LoginFailed::new);

        if(!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return user;
    }
}
