package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;

public interface IUserService {
    String createUser(CreateUserRequest request);
}
