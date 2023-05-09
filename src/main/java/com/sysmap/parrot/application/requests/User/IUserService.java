package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserResponse;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import com.sysmap.parrot.domain.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request);
    GetUserResponse getUserById(String Id);
    UpdateUserResponse updateUserById(String id, UpdateUserRequest request);
    String deleteUserById(String id);
    void uploadPhoto(String id, MultipartFile photo);
    User getUser(String email);
    User findUserById(UUID id);
    List<User> getAllUsers();
}
