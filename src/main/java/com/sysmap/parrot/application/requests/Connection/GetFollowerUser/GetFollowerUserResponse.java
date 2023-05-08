package com.sysmap.parrot.application.requests.Connection.GetFollowerUser;

import lombok.Data;

import java.util.UUID;

@Data
public class GetFollowerUserResponse {
    private UUID userId;
    public GetFollowerUserResponse(UUID userId) {
        this.userId = userId;
    }
}
