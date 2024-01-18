package com.user.UserGi.service;

import com.user.UserGi.entity.UserEntity;
import com.user.UserGi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository repository = new UserRepository();

    public List<UserEntity> getUsers() {
        return repository.getUsers();
    }

    public UserEntity addUser(final UserEntity user) {
        return repository.addUser(user);
    }

    public UserEntity getUserById(final Long userId) {
        return repository.getUserById(userId);
    }

    public List<UserEntity> getUser() {
        return repository.getUsers();
    }
}