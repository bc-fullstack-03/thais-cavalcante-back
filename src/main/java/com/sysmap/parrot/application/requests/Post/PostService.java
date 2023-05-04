package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.domain.embedded.Comment;
import com.sysmap.parrot.domain.entities.Post;
import com.sysmap.parrot.infrastructure.data.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<GetPostResponse> getPosts(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var posts = _postRepository.findAll(paging);
        List<GetPostResponse> postList = new ArrayList<>();

        for (Post post : posts) {
            var postResponse = new GetPostResponse(
                    post.getId(),
                    post.getContent(),
                    post.getImage(),
                    post.getCreatedAt(),
                    post.getAuthorId(),
                    post.getComments(),
                    post.getLikes()
            );
            postList.add(postResponse);
        }

        return postList;
    }

    public GetPostResponse getPostById(String id) {
        var transformedId = UUID.fromString(id);
        var post = _postRepository.findById(transformedId).get();

        var response = new GetPostResponse(
                post.getId(),
                post.getContent(),
                post.getImage(),
                post.getCreatedAt(),
                post.getAuthorId(),
                post.getComments(),
                post.getLikes()
        );

        return response;
    }

    public String deletePostById(String id) {
        var uuid = UUID.fromString(id);
        var post = _postRepository.findById(uuid).get();

        _postRepository.delete(post);

        var response = "Post excluído com sucesso";
        return response;
    }

    public String createCommentToPost(String authorId, String postId, CreateCommentRequest request) {
        var authorUuid = UUID.fromString(authorId);
        var postUuid = UUID.fromString(postId);

        var post = _postRepository.findById(postUuid).get();
        var comment = new Comment(request.content, authorUuid);

        post.getComments().add(comment);
        _postRepository.save(post);
        return comment.getId().toString();
    }
}
