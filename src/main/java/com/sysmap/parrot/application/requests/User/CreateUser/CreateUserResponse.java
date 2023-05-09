package com.sysmap.parrot.application.requests.User.CreateUser;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserResponse {
    public UUID id;
    public CreateUserResponse(UUID id) {
        this.id = id;
    }
}
