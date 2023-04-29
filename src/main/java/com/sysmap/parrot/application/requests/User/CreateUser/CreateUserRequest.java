package com.sysmap.parrot.application.requests.User.CreateUser;

import lombok.Data;

@Data
public class CreateUserRequest {
    public String name;
    public String email;
    public String password;
}
