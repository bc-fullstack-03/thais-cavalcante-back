package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;

import java.util.List;

public interface IPostService {
    String createPost(String authorId, CreatePostRequest request);
    List<GetPostResponse> getPosts(Integer page, Integer size);
    GetPostResponse getPostById(String id);
    String deletePostById(String id);
}
