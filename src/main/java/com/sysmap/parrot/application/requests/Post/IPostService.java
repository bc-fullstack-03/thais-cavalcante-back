package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;

public interface IPostService {
    String createPost(String authorId, CreatePostRequest request);
}
