package com.minderaschool.UserGiDataBase.repositoy;

import com.minderaschool.UserGiDataBase.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
