package com.minderaschool.UserGiDataBase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DtoCreateUser {
    private String username;
    private String email;
    private String password;

}
