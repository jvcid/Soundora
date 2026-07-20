package com.br.Soundora.api.handler.controller;

import com.br.Soundora.core.dto.CreateUserDTO;
import com.br.Soundora.core.dto.UserDTO;
import com.br.Soundora.core.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @RequestBody CreateUserDTO dto) {

        return ResponseEntity.ok(userService.createUser(dto));
    }
}