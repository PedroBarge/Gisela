package com.minderaschool.UserGiDataBase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class UserGiDataBaseApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    UserEntity user1 = new UserEntity(1, "User1", "Password1");
    UserEntity user2 = new UserEntity(2, "User2", "Password2");
    UserEntity user3 = new UserEntity(3, "User3", "Password3");

    @Test
    void testAddUser() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(4)
                .username("User4")
                .password("Password4")
                .build();

        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("User4")));
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));
        Mockito.when(userRepository.findAll()).thenReturn(listUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].username", is("User2")));
    }

    @Test
    void testGetUser() {

    }

    @Test
    void testUpdateUser() {

    }

    @Test
    void testDeleteUser() {

    }
}
