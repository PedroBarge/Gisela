package com.user.UserGi.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class UserEntity {
    private Long id;
    private String username;
    private String password;
}
