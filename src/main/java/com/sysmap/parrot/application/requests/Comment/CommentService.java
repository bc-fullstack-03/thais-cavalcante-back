package com.sysmap.parrot.application.requests.Comment;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Post.IPostService;
import com.sysmap.parrot.domain.embedded.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService implements ICommentService{

    @Autowired
    private IPostService _postService;

    public String createComment(String authorId, String postId, CreateCommentRequest request) {
        var authorUuid = UUID.fromString(authorId);
        var postUuid = UUID.fromString(postId);

        var post = _postService.findPostById(postUuid);
        var comment = new Comment(request.content, authorUuid);

        post.getComments().add(comment);
        _postService.savePost(post);
        return comment.getId().toString();
    }
}
