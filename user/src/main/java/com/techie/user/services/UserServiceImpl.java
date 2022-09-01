package com.techie.user.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.techie.user.dtos.requests.AddUserRequest;
import com.techie.user.dtos.requests.VerificationMessageRequest;
import com.techie.user.dtos.responses.AddUserResponse;
import com.techie.user.events.SendMessageEvent;
import com.techie.user.models.ImageUrl;
import com.techie.user.models.User;
import com.techie.user.repositories.ImageRepo;
import com.techie.user.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.techie.user.services.SecurityConstants.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ImageRepo imageRepo;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public AddUserResponse add(AddUserRequest request) throws IOException {
        User user = new User();
        user.setEmail(request.getEmail());

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET));
        File file = convertMultiPartToFile(request.getFile());
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setUrl((String)uploadResult.get("url"));
        ImageUrl savedImage= imageRepo.save(imageUrl);

        user.setImage(savedImage);

        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());

        User savedUser = userRepo.save(user);

        AddUserResponse userResponse = new AddUserResponse();
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setMessage("Dear "+savedUser.getEmail()+ ", your account has been created successfully");


        return userResponse;

    }



    private File convertMultiPartToFile(MultipartFile file) throws IOException, IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private void userCanReceiveEmail(String host, User user){
        VerificationMessageRequest message = VerificationMessageRequest.builder()
                .subject("VERIFY EMAIL")
                .sender("ehizman.tutoredafrica@gmail.com")
                .receiver(user.getEmail())
                .domainUrl(host)

                .usersFullName(String.format("%s %s", user.getUsername(), user.getEmail()))
                .build();
        SendMessageEvent event = new SendMessageEvent(message);
        applicationEventPublisher.publishEvent(event);
    }
}
