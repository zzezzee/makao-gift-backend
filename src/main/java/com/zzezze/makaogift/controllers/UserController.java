package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.dtos.UserRegistrationDto;
import com.zzezze.makaogift.exceptions.RegisterFailed;
import com.zzezze.makaogift.exceptions.UsernameAlreadyExist;
import com.zzezze.makaogift.services.CreateUserService;
import com.zzezze.makaogift.services.GetUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private GetUserService getUserService;
    private CreateUserService createUserService;

    public UserController(GetUserService getUserService, CreateUserService createUserService) {
        this.getUserService = getUserService;
        this.createUserService = createUserService;
    }

    @GetMapping("/me")
    public UserDto user(
            @RequestAttribute("username") String username
    ) {
        UserDto userDto = getUserService.detail(username);

        return userDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(
            @RequestBody UserRegistrationDto userRegistrationDto
    ) {
        UserDto userDto = createUserService.create(
                userRegistrationDto.getName(),
                userRegistrationDto.getUsername(),
                userRegistrationDto.getPassword());

        return userDto;
    }

    @ExceptionHandler(UsernameAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usernameAlreadyExist() {
        return "Username Already Exist";
    }

    @ExceptionHandler(RegisterFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String registerFailed() {
        return "Register failed";
    }
}
