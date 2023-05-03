package com.sysmap.parrot.domain.embedded;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {
    private UUID id;
    private String content;
    private Date createdAt;
    private UUID authorId;
    private List<Like> likes;

    public Comment (String content, UUID authorId) {
        this.setId();
        this.createdAt = getCurrentDateTime();
        this.content = content;
        this.authorId = authorId;
        this.likes = new ArrayList<>();
    }

    private Date getCurrentDateTime() {
        return new Date();
    }
    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

}
