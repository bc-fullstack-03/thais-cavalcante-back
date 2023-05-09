package com.sysmap.parrot.application.requests.Post.CreatePost;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePostResponse {
    public UUID id;
    public CreatePostResponse(UUID id) {
        this.id = id;
    }
}
