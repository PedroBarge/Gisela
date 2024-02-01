package com.minderaschool.UserGiDataBase.service;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.exception.UserMissArgs;
import com.minderaschool.UserGiDataBase.exception.UserNotFoundException;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto add(UserDto user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new UserMissArgs("User not complete");
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        repository.save(entity);
        return user;
    }

    public UserDto getUser(Integer id) {

        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        return new UserDto(user.getUsername(), user.getPassword());
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(UserEntity::getId))
                .map(userEntity -> new UserDto(userEntity.getUsername(), userEntity.getPassword()))
                .toList();
    }

    public void update(Integer id, UserDto updatedUser) {
        if (updatedUser.getUsername() == null || updatedUser.getPassword() == null) {
            throw new UserMissArgs("User not complete");
        }
        UserEntity existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        repository.save(existingUser);
    }

    public void updatePatch(Integer id, UserDto updatePatch) {
        if (updatePatch.getUsername() == null && updatePatch.getPassword() == null) {
            throw new UserMissArgs("User not complete");
        }

        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        if (updatePatch.getUsername() != null) {
            user.setUsername(updatePatch.getUsername());
        }
        if (updatePatch.getPassword() != null) {
            user.setPassword(updatePatch.getPassword());
        }
        repository.save(user);
    }

    public void deleteUser(Integer id) throws Exception {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User " + id + " not found");
        }
        repository.deleteById(id);
    }
}
