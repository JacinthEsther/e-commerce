package com.techie.user.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.techie.user.dtos.requests.AddUserRequest;
import com.techie.user.dtos.responses.AddUserResponse;
import com.techie.user.models.ImageUrl;
import com.techie.user.models.User;
import com.techie.user.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public AddUserResponse add(AddUserRequest request) throws IOException {
        User user = new User();
        user.setEmail(request.getEmail());

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dikhupelx",
                "api_key", "145123527243894",
                "api_secret", "yfQJGz3irKt7Ck2Lc3r2h2fwa_s"));
        File file = convertMultiPartToFile(request.getFile());
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setUrl((String)uploadResult.get("url"));

        user.setImage(imageUrl);
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());

        User savedUser = userRepo.save(user);
        AddUserResponse userResponse = new AddUserResponse();
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setMessage("Dear "+savedUser.getEmail()+ ", your account has been created successfully");


        return userResponse;

    }


//    public UploadResponseDto uploadImages(MultipartFile multipartFile) throws IOException {
//
//        UploadResponseDto responseDto = new UploadResponseDto();
//        responseDto.setUrl((String) uploadResult.get("url"));
//        ImageUrl imageUrl = new ImageUrl();
//        imageUrl.setUrl(responseDto.getUrl());
//        imageUrlRepository.save(imageUrl);
//        return  responseDto;
//    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException, IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
