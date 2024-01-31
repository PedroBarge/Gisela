package com.minderaschool.UserGiDataBase;

import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserGiDataBaseApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    //-----Variables Area-----\\
    private final ObjectMapper mapper = new ObjectMapper();
    List<UserEntity> listUser;

    @BeforeEach
    void init() {
        UserEntity user1 = new UserEntity(1, "User1", "Password1");
        UserEntity user2 = new UserEntity(2, "User2", "Password2");
        UserEntity user3 = new UserEntity(3, "User3", "Password3");
        listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
    }

    @AfterEach
    void teardown() {
        listUser.clear();
    }

    //-----TEST AREA-----\\
    @Test
    void testAddUserOk() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(4)
                .username("User4")
                .password("Password4")
                .build();

        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void testAddUserNotOk() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(4)
                .build();

        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllUsersOk() throws Exception {
        Mockito.when(userRepository.findAll()).thenReturn(listUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].username", is("User2")));
    }

    @Test
    void testGetUserOk() throws Exception {
        int idToSearchTest = 1;

        Mockito.when(userRepository.getReferenceById(idToSearchTest)).thenReturn(listUser.get(idToSearchTest));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}", idToSearchTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("User2")))
                .andExpect(jsonPath("$.password", is("Password2")));
    }

    @Test
    void testGetUserNotOk() throws Exception {
        int idToSearchTest = 4;

        Mockito.when(userRepository.getReferenceById(idToSearchTest)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}", idToSearchTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteUserOk() throws Exception {
        int userIdToDelete = 1;
        Mockito.when(userRepository.findById(userIdToDelete)).thenReturn(Optional.of(listUser.get(userIdToDelete)));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/user/delete/{id}", userIdToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUserOk() throws Exception {
        UserEntity updateUser = UserEntity.builder()
                .id(1)
                .username("NewName")
                .password("NewPassword")
                .build();

    }
}
