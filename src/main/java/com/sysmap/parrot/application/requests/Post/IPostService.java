package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Post.CreatePost.CreatePostRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {
    String createPost(String content, MultipartFile photo) throws Exception;
    List<GetPostResponse> getPosts(Integer page, Integer size);
    GetPostResponse getPostById(String id);
    String deletePostById(String id) throws Exception;
    String createCommentToPost(String postId, CreateCommentRequest request) throws Exception;
    String deleteCommentToPost(String postId, String commentId) throws Exception;
    String likeOrRemoveLikeFromPost(String postId) throws Exception;
    String likeOrRemoveLikeFromComment(String postId, String commentId) throws Exception;
}
