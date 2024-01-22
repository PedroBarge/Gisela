package com.minderaschool.UserGiDataBase.controller;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.service.UserService;
import org.hibernate.Internal;
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
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping
    public UserDto add(@RequestBody UserDto user) {
        service.add(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Integer id, UserDto userDto){
        service.update(id, userDto);
    }
}
