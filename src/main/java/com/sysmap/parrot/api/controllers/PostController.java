package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.application.requests.Post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping()
    public ResponseEntity<String> createPost(@RequestParam String authorId, @RequestBody CreatePostRequest request) {
        var response = _postService.createPost(authorId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping()
    public ResponseEntity<List<GetPostResponse>> getPosts(@RequestParam Integer page, @RequestParam Integer size) {
        var response = _postService.getPosts(page, size);

        return ResponseEntity.ok().body(response);
    }
}
