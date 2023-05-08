package com.sysmap.parrot.application.requests.Connection.GetUserToConnectResponse;

import lombok.Data;

import java.util.UUID;

@Data
public class GetUserToConnectResponse {
    private UUID userId;
    private String name;
    private Number followingCount;
    private Number followersCount;

    public GetUserToConnectResponse (UUID userId, String name, Number followingCount, Number followersCount) {
        this.userId = userId;
        this.name = name;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
    }
}
