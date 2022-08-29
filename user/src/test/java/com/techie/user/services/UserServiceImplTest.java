package com.techie.user.services;

import com.techie.user.ModuleConfigTest;
import com.techie.user.dtos.requests.AddUserRequest;
import com.techie.user.dtos.responses.AddUserResponse;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
//        MultipartFile multipartFile = new MultipartFile() {
//            private String name;
//            private String url;
//            @Override
//            public String getName() {
//                return this.name;
//            }
//
//            @Override
//            public String getOriginalFilename() {
//                return null;
//            }
//
//            @Override
//            public String getContentType() {
//                return null;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public long getSize() {
//                return 0;
//            }
//
//            @Override
//            public byte[] getBytes() throws IOException {
//                return new byte[0];
//            }
//
//            @Override
//            public InputStream getInputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void transferTo(File dest) throws IOException, IllegalStateException {
//
//            }
//        };
        AddUserRequest user = AddUserRequest.builder().username("Jay")
                .email("agbonirojacinta@gmail.com")
                .phoneNumber("09095861220").password("23456").build();
        AddUserResponse response=userService.add(user);
        assertEquals(response.getEmail(),("agbonirojacinta@gmail.com"));


    }
}