package com.minderaschool.UserGiDataBase.controller;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.exception.UserNotFoundException;
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

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping("/add")
    public UserDto add(@RequestBody UserDto user) {
        try {
            if (user.getUsername() != null && user.getPassword() !=null) {
                service.add(user);
                return user;
            }
            if (user.getPassword() == null || user.getUsername() == null) {
                throw new UserNotFoundException("Incompleto user");
            }
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Integer id) throws Exception {
        service.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        service.update(id, userDto);
    }

   @PatchMapping("/update/patch/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody UserDto userDto){
        service.updatePatch(id,userDto);
   }
}
