package com.sysmap.parrot.application.requests.User.GetUser;

import java.util.UUID;

public class GetUserResponse {
    public UUID id;
    public String name;
    public String email;

    public GetUserResponse(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
