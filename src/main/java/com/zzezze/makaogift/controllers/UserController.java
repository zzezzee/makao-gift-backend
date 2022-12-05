package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.UserDto;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.services.GetUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("/me")
    public UserDto user(
            @RequestAttribute("username") String username
    ) {
        UserDto userDto = getUserService.detail(username);

        return userDto;
    }
}
