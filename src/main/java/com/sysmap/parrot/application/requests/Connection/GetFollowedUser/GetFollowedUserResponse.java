package com.sysmap.parrot.application.requests.Connection.GetFollowedUser;

import lombok.Data;

import java.util.UUID;

@Data
public class GetFollowedUserResponse {
    private UUID userId;
    public GetFollowedUserResponse(UUID userId) {
        this.userId = userId;
    }
}
