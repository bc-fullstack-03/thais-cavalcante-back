package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.application.requests.Post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping()
    public ResponseEntity<String> createPost(@RequestParam(name = "content", required = false) String content, @RequestPart(name = "photo", required = false) MultipartFile photo) throws Exception {
        var response = _postService.createPost(content, photo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping()
    public ResponseEntity<List<GetPostResponse>> getPosts(@RequestParam Integer page, @RequestParam Integer size) {
        var response = _postService.getPosts(page, size);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable String id) {
        var response = _postService.getPostById(id);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) throws Exception {
        var response = _postService.deletePostById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> createCommentToPost(@PathVariable String postId, @RequestBody CreateCommentRequest request) throws Exception {
        var response = _postService.createCommentToPost(postId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentToPost(@PathVariable String postId, @PathVariable String commentId) throws Exception {
        var response = _postService.deleteCommentToPost(postId, commentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<String> likeOrRemoveLikeFromPost(@PathVariable String postId) throws Exception {
        var response = _postService.likeOrRemoveLikeFromPost(postId);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{postId}/comments/{commentId}/likes")
    public ResponseEntity<String> likeOrRemoveLikeFromComment(@PathVariable String postId, @PathVariable String commentId) throws Exception {
        var response = _postService.likeOrRemoveLikeFromComment(postId, commentId);

        return ResponseEntity.ok().body(response);
    }
}
