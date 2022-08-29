package com.techie.user.services;

import com.techie.user.dtos.requests.AddUserRequest;
import com.techie.user.dtos.responses.AddUserResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface UserService {

    AddUserResponse add(AddUserRequest user) throws IOException;
}
