package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.LoginRequestDto;
import com.zzezze.makaogift.dtos.LoginResultDto;
import com.zzezze.makaogift.exceptions.LoginFailed;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.services.LoginService;
import com.zzezze.makaogift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public SessionController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        User user = loginService.login(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword());

        String accessToken = jwtUtil.encode(loginRequestDto.getUsername());

        return new LoginResultDto(accessToken, user.amount());
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login failed";
    }
}

