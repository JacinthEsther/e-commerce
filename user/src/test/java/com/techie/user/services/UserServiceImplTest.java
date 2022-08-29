package com.techie.user.services;

import com.techie.user.ModuleConfigTest;
import com.techie.user.dtos.requests.AddUserRequest;
import com.techie.user.dtos.responses.AddUserResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = ModuleConfigTest.class)
@DataMongoTest
class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    void addUser() throws IOException {

        File file = new File("src/main/resources/Screenshot (40).png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));

        AddUserRequest user = AddUserRequest.builder().username("Jay")
                .email("agbonirojacinta@gmail.com").file(multipartFile)
                .phoneNumber("09095861220").password("23456").build();
        AddUserResponse response=userService.add(user);
        assertEquals(response.getEmail(),("agbonirojacinta@gmail.com"));


    }
}