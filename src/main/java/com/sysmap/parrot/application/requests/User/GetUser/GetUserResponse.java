package com.sysmap.parrot.application.requests.User.GetUser;

import lombok.Data;

import java.util.UUID;

@Data
public class GetUserResponse {
    public UUID id;
    public String name;
    public String email;
    public String pictureUrl;

    public GetUserResponse(UUID id, String name, String email, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }
}
