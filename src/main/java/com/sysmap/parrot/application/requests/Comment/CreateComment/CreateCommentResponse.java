package com.sysmap.parrot.application.requests.Comment.CreateComment;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentResponse {
    public UUID id;
    public CreateCommentResponse(UUID id) {
        this.id = id;
    }
}
