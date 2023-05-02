package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;

public interface IUserService {
    String createUser(CreateUserRequest request);
    GetUserResponse getUserById(String Id);
}
