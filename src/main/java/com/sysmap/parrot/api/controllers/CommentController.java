package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    private ICommentService _commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> createComment(@PathVariable String postId, @RequestParam String authorId, @RequestBody CreateCommentRequest request) {
        var response = _commentService.createComment(authorId, postId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
