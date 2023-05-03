package com.sysmap.parrot.application.requests.Post.GetPost;

import com.sysmap.parrot.domain.embedded.Comment;
import com.sysmap.parrot.domain.embedded.Like;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GetPostResponse {
    public UUID id;
    public String content;
    public String image;
    public Date createdAt;
    public UUID authorId;
    public List<Comment> comments;
    public List<Like> likes;

    public GetPostResponse(UUID id, String content, String image, Date createdAt, UUID authorId, List<Comment> comments, List<Like> likes) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
        this.authorId = authorId;
        this.comments = comments;
        this.likes = likes;
    }
}
