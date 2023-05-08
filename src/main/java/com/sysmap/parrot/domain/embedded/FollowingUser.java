package com.sysmap.parrot.domain.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class FollowingUser {
    private UUID userId;

    public FollowingUser (UUID userId) {
        this.userId = userId;
    }
}
