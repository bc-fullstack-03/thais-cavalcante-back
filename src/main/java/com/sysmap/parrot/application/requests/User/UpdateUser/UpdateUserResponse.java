package com.sysmap.parrot.application.requests.User.UpdateUser;

import java.util.UUID;

public class UpdateUserResponse {
    public UUID id;
    public String name;
    public String email;
    public String password;
    public String profileUrl;

    public UpdateUserResponse(UUID id, String name, String email, String password, String profileUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileUrl = profileUrl;
    }
}