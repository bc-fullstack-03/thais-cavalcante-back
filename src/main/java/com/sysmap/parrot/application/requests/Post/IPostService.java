package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.domain.entities.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    String createPost(String authorId, CreatePostRequest request);
    List<GetPostResponse> getPosts(Integer page, Integer size);
    GetPostResponse getPostById(String id);
    String deletePostById(String id);
    Post findPostById(UUID id);
    void savePost(Post post);
}
