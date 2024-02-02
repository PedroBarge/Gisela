package com.minderaschool.UserGiDataBase;

import com.minderaschool.UserGiDataBase.entity.UserEntity;
import com.minderaschool.UserGiDataBase.repositoy.UserRepository;

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
    UserEntity user1 = new UserEntity(1, "User1", "Password1");
    UserEntity user2 = new UserEntity(2, "User2", "Password2");
    UserEntity user3 = new UserEntity(3, "User3", "Password3");

    //-----TEST AREA-----\\

    /**
     *
     * Test to add user sucess
     *
     */
    @Test
    void testAddUserOkShouldExpectStatusIsOk() throws Exception {
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
    //----------\\
    /**
     *
     * Test to add user don't have the body complete
     *
     */
    @Test
    void testAddUserNotOkShouldExpectStatusIsBadRequest() throws Exception {
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
    //----------\\
    /**
     *
     * Test to get all the users OK
     *
     */
    @Test
    void testGetAllUsersOkShouldExpectStatusIsOk() throws Exception {
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));
        Mockito.when(userRepository.findAll()).thenReturn(listUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].username", is("User2")));
    }
    //----------\\
    /**
     *
     * Test to get one user
     *
     */
    @Test
    void testGetUserOkShouldExpectStatusIsOk() throws Exception {
        int id = 1;
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(listUser.get(id)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("User2")))
                .andExpect(jsonPath("$.password", is("Password2")));
    }
//----------\\
    /**
     *
     * Test to get one user but not ok
     *
     */
    @Test
    void testGetUserNotOkShouldExpectStatusIsBadRequest() throws Exception {
        int idToSearchTest = 4;

        Mockito.when(userRepository.getReferenceById(idToSearchTest)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}", idToSearchTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
//----------\\
    /**
     *
     * Test to DELETE user sucess
     *
     */
    @Test
    void testDeleteUserOkShouldExpectStatusIsOk() throws Exception {
        int userIdToDelete = 1;
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));
        Mockito.when(userRepository.findById(userIdToDelete)).thenReturn(Optional.of(listUser.get(userIdToDelete)));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/user/delete/{id}", userIdToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteUserOkShouldExpectStatusIsBadRequest() throws Exception {
        int userIdToDelete = 1;
        Mockito.when(userRepository.findById(userIdToDelete)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/user/delete/{id}", userIdToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }
    //----------\\
    /**
     *
     * Test to UPDATE user sucess
     *
     */
    @Test
    void testUpdateUserOkShouldReturnOkStatus() throws Exception {
        int id = 1;
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("UPDATE")
                .password("UpdatePassword")
                .build();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable((listUser.get(id))));
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/user/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    //----------\\
    /**
     *
     * Test to UPDATE user with bad request
     *
     */
    @Test
    void testUpdateUserNotOkShouldReturnBadRequest() throws Exception {
        int id = 1;
        List<UserEntity> listUser = new ArrayList<>(Arrays.asList(user1, user2, user3));
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("UPDATE")
                .password(null)
                .build();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable((listUser.get(id))));
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/user/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }
    //----------\\
    /**
     *
     * Test to UPDATE user with bad request id NULL
     *
     */
    @Test
    void testUpdateUserNotOkShouldReturnBadRequestIdNull() throws Exception {
        int id = 1;
        UserEntity userEntity = UserEntity.builder()
                .id(null)
                .username("UPDATE")
                .password("password")
                .build();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/user/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    //TODO: Update Patch
}