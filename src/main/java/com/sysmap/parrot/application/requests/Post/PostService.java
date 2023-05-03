package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.domain.entities.Post;
import com.sysmap.parrot.infrastructure.data.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository _postRepository;

    public String createPost(String authorId, CreatePostRequest request) {
        var authorUuid = UUID.fromString(authorId);

        var post = new Post(request.content, request.image, authorUuid);

        _postRepository.save(post);

        return post.getId().toString();
    }
}
