package com.sysmap.parrot.domain.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class Like {
    private UUID authorId;

    public Like (UUID authorId) {
        this.authorId = authorId;
    }
}
