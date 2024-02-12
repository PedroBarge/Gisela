package com.minderaschool.UserGiDataBase.service;

import com.minderaschool.UserGiDataBase.dto.DtoCreateUser;
import com.minderaschool.UserGiDataBase.dto.DtoGetAll;
import com.minderaschool.UserGiDataBase.dto.DtoGetOneUser;
import com.minderaschool.UserGiDataBase.dto.DtoUpdate;
import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.exception.UserMissArgsException;
import com.minderaschool.UserGiDataBase.exception.UserNotFoundException;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public DtoCreateUser add(DtoCreateUser user) {
        if (user.getUsername() == null || user.getEmail() == null ||user.getPassword() == null) {
            throw new UserMissArgsException("User body not complete");
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        repository.save(entity);
        return user;
    }

    public DtoGetOneUser getUser(Integer id) {

        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        return new DtoGetOneUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public List<DtoGetAll> getAllUsers() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(UserEntity::getId))
                .map(userEntity -> new DtoGetAll(userEntity.getId(), userEntity.getUsername()))
                .toList();
    }

    public void update(Integer id, DtoUpdate updatedUser) {

        UserEntity checkID = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        if ((updatedUser.getUsername() == null || updatedUser.getUsername().isEmpty())
                || (updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty())) {
            throw new UserMissArgsException("User not complete or empty");
        }

        UserEntity userEntity = new UserEntity(checkID.getId(), updatedUser.getUsername(), checkID.getEmail(), updatedUser.getUsername());

        repository.save(userEntity);
    }

    public void updatePatch(Integer id, DtoUpdate updatePatch) {
        if ((updatePatch.getUsername() == null || updatePatch.getUsername().isEmpty())
                || (updatePatch.getPassword() == null || updatePatch.getPassword().isEmpty())) {
            throw new UserMissArgsException("User not complete or empty");
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

    public void deleteUser(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User " + id + " not found");
        }
        repository.deleteById(id);
    }
}
