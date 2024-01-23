package com.minderaschool.UserGiDataBase;

import com.minderaschool.UserGiDataBase.dto.UserDto;
import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserGiDataBaseApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Test
    void testAddUser() {
        UserDto userDto = new UserDto("testUser", "password123");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
    }
}
