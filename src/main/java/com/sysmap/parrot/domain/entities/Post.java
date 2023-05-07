package com.sysmap.parrot.domain.entities;

import com.sysmap.parrot.domain.embedded.Comment;
import com.sysmap.parrot.domain.embedded.Like;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Post {
    private UUID id;
    private String content;
    private String image;
    private Date createdAt;
    private UUID authorId;
    private List<Comment> comments;
    private List<Like> likes;

    public Post (String content, UUID authorId) {
        this.setId();
        this.createdAt = getCurrentDateTime();
        this.content = content;
        this.authorId = authorId;
        this.comments = new ArrayList<>();
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
