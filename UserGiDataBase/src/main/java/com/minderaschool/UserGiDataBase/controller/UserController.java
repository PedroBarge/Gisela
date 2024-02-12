package com.minderaschool.UserGiDataBase.controller;

import com.minderaschool.UserGiDataBase.dto.DtoCreateUser;
import com.minderaschool.UserGiDataBase.dto.DtoGetAll;
import com.minderaschool.UserGiDataBase.dto.DtoGetOneUser;
import com.minderaschool.UserGiDataBase.dto.DtoUpdate;
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

    @GetMapping()
    public List<DtoGetAll> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public DtoGetOneUser getById(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping()
    public void add(@RequestBody DtoCreateUser user) {
        service.add(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id){
        service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody DtoUpdate dtoCreateUser) {
        service.update(id, dtoCreateUser);
    }

    @PatchMapping("/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody DtoUpdate dtoCreateUser) {
        service.updatePatch(id, dtoCreateUser);
    }
}
