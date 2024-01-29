package com.minderaschool.UserGiDataBase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Builder
@Getter
@Entity
@Table(name="user_table")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;

    public UserEntity(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
    }
}
