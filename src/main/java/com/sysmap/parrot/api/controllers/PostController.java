package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentResponse;
import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostResponse;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.application.requests.Post.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Post")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping()
    @Operation(summary = "Create post")
    public ResponseEntity<CreatePostResponse> createPost(@RequestParam(name = "content", required = false) String content, @RequestPart(name = "photo", required = false) MultipartFile photo) {
        var response = _postService.createPost(content, photo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping()
    @Operation(summary = "Get all posts with pagination")
    public ResponseEntity<List<GetPostResponse>> getPosts(@RequestParam Integer page, @RequestParam Integer size) {
        var response = _postService.getPosts(page, size);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable String id) {
        var response = _postService.getPostById(id);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post by id")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        var response = _postService.deletePostById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PostMapping("/{postId}/comments")
    @Operation(summary = "Create comment to post")
    public ResponseEntity<CreateCommentResponse> createCommentToPost(@PathVariable String postId, @RequestBody CreateCommentRequest request) {
        var response = _postService.createCommentToPost(postId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @Operation(summary = "Delete comment from post")
    public ResponseEntity<String> deleteCommentToPost(@PathVariable String postId, @PathVariable String commentId) {
        var response = _postService.deleteCommentToPost(postId, commentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PostMapping("/{postId}/likes")
    @Operation(summary = "Add/remove like from post")
    public ResponseEntity<String> likeOrRemoveLikeFromPost(@PathVariable String postId) {
        var response = _postService.likeOrRemoveLikeFromPost(postId);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{postId}/comments/{commentId}/likes")
    @Operation(summary = "Add/remove like from comment ")
    public ResponseEntity<String> likeOrRemoveLikeFromComment(@PathVariable String postId, @PathVariable String commentId) {
        var response = _postService.likeOrRemoveLikeFromComment(postId, commentId);

        return ResponseEntity.ok().body(response);
    }
}
