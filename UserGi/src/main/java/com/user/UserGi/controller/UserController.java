package com.user.UserGi.controller;

import com.user.UserGi.entity.UserEntity;
import com.user.UserGi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/getuser")
    public List<UserEntity> getUser() {
        return service.getUser();
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addUser(@RequestBody final UserEntity user) {
        var newUser = service.addUser(user);
        return ResponseEntity.status(201)
                .body(newUser);
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable final String username, @PathVariable final Long userId) {
        System.out.println(username);
        return service.getUserById(userId);
    }

}
