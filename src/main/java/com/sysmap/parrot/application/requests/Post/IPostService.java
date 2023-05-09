package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentResponse;
import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostResponse;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.domain.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {
    CreatePostResponse createPost(String content, MultipartFile photo);
    List<GetPostResponse> getPosts(Integer page, Integer size);
    GetPostResponse getPostById(String id);
    String deletePostById(String id);
    CreateCommentResponse createCommentToPost(String postId, CreateCommentRequest request);
    String deleteCommentToPost(String postId, String commentId);
    String likeOrRemoveLikeFromPost(String postId);
    String likeOrRemoveLikeFromComment(String postId, String commentId);
    List<Post> getPosts();
}
