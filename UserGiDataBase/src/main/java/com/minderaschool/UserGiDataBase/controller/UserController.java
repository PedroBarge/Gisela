package com.minderaschool.UserGiDataBase.controller;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<UserDto> getAll() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserDto add(@RequestBody UserDto user) {
        service.add(user);
        return user;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return service.getUser(id);
    }
}
