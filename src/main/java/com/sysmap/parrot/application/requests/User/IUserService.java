package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    String createUser(CreateUserRequest request);
    GetUserResponse getUserById(String Id);
    UpdateUserResponse updateUserById(String id, UpdateUserRequest request);
    String deleteUserById(String id);
    void uploadPhoto(MultipartFile photo) throws Exception;
}
