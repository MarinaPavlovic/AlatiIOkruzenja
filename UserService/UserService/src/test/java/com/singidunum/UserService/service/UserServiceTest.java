package com.singidunum.UserService.service;

import com.singidunum.UserService.DTO.LoginParams;
import com.singidunum.UserService.DTO.UserDTO;
import com.singidunum.UserService.entity.User;
import com.singidunum.UserService.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest

class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void createUser() {

        var user = new UserDTO(null,"Test", "Test","Test","Test@test.com");
        assertInstanceOf(UserDTO.class, userService.createUser(user));
        var createdUser = userService.createUser(user);
        assertEquals("Test" , createdUser.getFullName());
        assertEquals("Test" , createdUser.getUsername());
        assertEquals("Test" , createdUser.getPassword());


    }

    @Test
    void getUserWithId4() {
        assertInstanceOf(UserDTO.class, userService.getUser(4));
    }

    @Test
    void findUserWithUsernameMarinaAndPassword123() {
        var loginParams = new LoginParams("Marina","123");
        assertInstanceOf(UserDTO.class,userService.findUser(loginParams));
        UserDTO user = userService.findUser(loginParams);
        assertEquals("Marina", user.getUsername());
        assertEquals("123", user.getPassword());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(4);
        assertEquals(null , userService.getUser(4));
    }
}