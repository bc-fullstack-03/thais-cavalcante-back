package com.sysmap.parrot.application.requests.Post;

import com.sysmap.parrot.application.requests.Comment.CreateComment.CreateCommentRequest;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.application.requests.fileUpload.IFileUploadService;
import com.sysmap.parrot.domain.embedded.Comment;
import com.sysmap.parrot.domain.embedded.Like;
import com.sysmap.parrot.domain.entities.Post;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.exception.BadRequestException;
import com.sysmap.parrot.infrastructure.data.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository _postRepository;

    @Autowired
    private IFileUploadService _fileUploadService;

    private User getAuthenticatedUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public String createPost(String content, MultipartFile photo) throws Exception {
        var authenticatedUser = getAuthenticatedUser();

        var post = new Post(content, authenticatedUser.getId());

        String photoUri = null;

        if(photo != null) {

            var fileName = post.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
            try {
                photoUri = _fileUploadService.upload(photo, fileName);
            } catch (Exception e) {
                throw new Exception("Erro ao fazer upload da foto", e);
            }
        }

        post.setImage(photoUri);
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
        var post = _postRepository.findById(UUID.fromString(id)).orElse(null);

        if (post == null) {
            throw new BadRequestException("Post não encontrado");
        }

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

    public String deletePostById(String id) throws Exception {
            var authenticatedUser = getAuthenticatedUser();
            var post = _postRepository.findById(UUID.fromString(id)).orElse(null);

            if (post == null) {
                throw new Exception("Post não encontrado");
            }

            if (!authenticatedUser.getId().equals(post.getAuthorId())) {
                throw new Exception("Você não tem permissão para excluir este post");
            }

            _postRepository.delete(post);

            return "Post excluído com sucesso";
    }

    public String createCommentToPost(String postId, CreateCommentRequest request) throws Exception {
        var authenticatedUser = getAuthenticatedUser();
        var post = _postRepository.findById(UUID.fromString(postId)).orElse(null);

        if (post == null) {
            throw new Exception("Post não encontrado");
        }

        var comment = new Comment(request.content, authenticatedUser.getId());

        post.getComments().add(comment);
        _postRepository.save(post);
        return comment.getId().toString();
    }

    public String deleteCommentToPost(String postId, String commentId) throws Exception {
        var authenticatedUser = getAuthenticatedUser();
        var post = _postRepository.findById(UUID.fromString(postId)).orElse(null);

        if (post == null) {
            throw new Exception("Post não encontrado");
        }

        var comment = post.getComments().stream().filter(c -> c.getId().equals(UUID.fromString(commentId))).findFirst();

        if (comment.isEmpty()) {
            throw new Exception("Comentário não encontrado");
        }

        var commentToDelete = comment.get();

        if (!authenticatedUser.getId().equals(commentToDelete.getAuthorId()) && !authenticatedUser.getId().equals(post.getAuthorId())) {
            throw new Exception("Você não tem permissão para excluir este comentário");
        }

        post.getComments().remove(commentToDelete);
        _postRepository.save(post);

        return "Comentário excluído com sucesso!";
    }

    public String likeOrRemoveLikeFromPost(String postId) throws Exception {
        var authenticatedUser = getAuthenticatedUser();
        var post = _postRepository.findById(UUID.fromString(postId)).orElse(null);

        if (post == null) {
            throw new Exception("Post não encontrado!");
        }

        var like = post.getLikes().stream().filter(l -> l.getAuthorId().equals(authenticatedUser.getId())).findFirst();

        if (like.isEmpty()) {
            var likePost = post.getLikes().add(new Like(authenticatedUser.getId()));
            _postRepository.save(post);
            return "Post curtido!";
        }

        post.getLikes().remove(like.get());
        _postRepository.save(post);
        return "Curtida removida do Post!";
    }

    public String likeOrRemoveLikeFromComment(String postId, String commentId) throws Exception {
        var authenticatedUser = getAuthenticatedUser();
        var post = _postRepository.findById(UUID.fromString(postId)).orElse(null);

        if (post == null) {
            throw new Exception("Post não encontrado");
        }

        var comment = post.getComments().stream().filter(c -> c.getId().equals(UUID.fromString(commentId))).findFirst();

        if (comment.isEmpty()) {
            throw new Exception("Comentário não encontrado");
        }

        var commentToLikeOrRemoveLike = comment.get();

        var like = commentToLikeOrRemoveLike.getLikes().stream().filter(l -> l.getAuthorId().equals(authenticatedUser.getId())).findFirst();

        if (like.isEmpty()) {
            commentToLikeOrRemoveLike.getLikes().add(new Like(authenticatedUser.getId()));
            _postRepository.save(post);
            return "Comentário curtido!";
        }

        commentToLikeOrRemoveLike.getLikes().remove(like.get());
        _postRepository.save(post);
        return "Curtida removida do Comentário!";
    }
}
