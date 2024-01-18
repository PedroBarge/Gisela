package com.user.UserGi.repository;

import java.util.LinkedList;
import java.util.List;
import com.user.UserGi.entity.UserEntity;

public class UserRepository {
    private Long count = 0L;
    private final List<UserEntity> users = new LinkedList<>();

    public List<UserEntity> getUsers() {
        return users;
    }

    public UserEntity addUser(final UserEntity user) {
        final var newUser = UserEntity.builder()
                .id(count)
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        users.add(newUser);
        count += 1;
        return newUser;
    }

    public UserEntity getUserById(final Long userId) {
        return users.get(Math.toIntExact(userId));
    }
}
