package com.techie.user.services;

import com.techie.user.ModuleConfigTest;
import com.techie.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = ModuleConfigTest.class)
@DataMongoTest
class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    void addUser(){
        User user = User.builder().username("Jay")
                .email("agbonirojacinta@gmail.com").image("")
                .phoneNumber("09095861220").build();
//        userService.add();


    }
}