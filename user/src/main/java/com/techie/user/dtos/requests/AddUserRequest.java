package com.techie.user.dtos.requests;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String username;
    private String email;
    private MultipartFile file;
    private String phoneNumber;
    private String password;
}
