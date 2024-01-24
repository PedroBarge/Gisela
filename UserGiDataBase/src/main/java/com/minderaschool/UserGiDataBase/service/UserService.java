package com.minderaschool.UserGiDataBase.service;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto add(UserDto user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        repository.save(entity);
        return user;
    }

    public UserDto getUser(Integer id) {
        UserEntity user = repository.getReferenceById(id);
        return new UserDto(user.getUsername(), user.getPassword());
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(userEntity -> new UserDto(userEntity.getUsername(), userEntity.getPassword()))
                .toList();
    }

    public void update(Integer id, UserDto updatedUser) {
        UserEntity existingUser = repository.getReferenceById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        repository.save(existingUser);
    }

    public void updatePassword(Integer id, UserDto updatePassword){
        UserEntity user = repository.getReferenceById(id);
        user.setPassword(updatePassword.getPassword());
        repository.save(user);
    }

    public void updateUsername(Integer id, UserDto updateUsername){
        UserEntity user = repository.getReferenceById(id);
        user.setUsername(updateUsername.getUsername());
        repository.save(user);
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}
