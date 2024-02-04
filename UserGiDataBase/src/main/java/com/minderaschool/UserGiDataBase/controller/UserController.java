package com.minderaschool.UserGiDataBase.controller;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.exception.UserNotFoundException;
import com.minderaschool.UserGiDataBase.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<UserDto> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping()
    public void add(@RequestBody UserDto user) {
        service.add(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) throws Exception {
        service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        service.update(id, userDto);
    }

    @PatchMapping("/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody UserDto userDto) {
        service.updatePatch(id, userDto);
    }
}
