package com.sysmap.parrot.application.requests.User.CreateUser;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotEmpty(message = "The User name cannot be empty")
    public String name;
    @NotEmpty(message = "The email cannot be empty")
    public String email;
    @NotEmpty(message = "The password cannot be empty")
    public String password;
}
