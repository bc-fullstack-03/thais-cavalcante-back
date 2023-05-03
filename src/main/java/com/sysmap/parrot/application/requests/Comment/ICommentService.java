package com.sysmap.parrot.application.requests.Comment;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;

public interface ICommentService {
    String createComment(String authorId, String postId, CreateCommentRequest request);
}
