package com.sysmap.parrot.domain.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class FollowerUser {
    private UUID userId;

    public FollowerUser (UUID userId) {
        this.userId = userId;
    }
}
