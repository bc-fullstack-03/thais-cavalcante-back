package com.sysmap.parrot.application.requests.Feed;

import com.sysmap.parrot.application.requests.Connection.IConnectionService;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import com.sysmap.parrot.application.requests.Post.IPostService;
import com.sysmap.parrot.domain.entities.Post;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.exception.notFoundException.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService implements IFeedService{

    @Autowired
    private IPostService _postService;

    @Autowired
    private IConnectionService _connectionService;

    private User getAuthenticatedUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public List<GetPostResponse> getFeed() {
        var authenticatedUser = getAuthenticatedUser();

        List<GetPostResponse> feed = new ArrayList<>();

        var posts = _postService.getPosts();

        var followingUsers = _connectionService.getFollowingList(authenticatedUser.getId().toString());

        if (followingUsers.isEmpty() && !posts.stream().anyMatch(post -> post.getAuthorId().equals(authenticatedUser.getId()))) {
            throw new NotFoundException("Nenhum post a ser exibido no feed");
        }

        boolean foundMatchingPost = false;
        for (Post post : posts) {
            if (post.getAuthorId() == null) {
                continue;
            }
            if (followingUsers.stream().anyMatch(user -> user.getUserId().equals(post.getAuthorId())) || post.getAuthorId().equals(authenticatedUser.getId())) {
                var getPostResponse = new GetPostResponse(
                        post.getId(),
                        post.getContent(),
                        post.getImage(),
                        post.getCreatedAt(),
                        post.getAuthorId(),
                        post.getComments(),
                        post.getLikes()
                );
                feed.add(getPostResponse);
                foundMatchingPost = true;
            }
        }

        if(!foundMatchingPost && !followingUsers.isEmpty()) {
            throw new NotFoundException("Nenhum post a ser exibido no feed");
        }
        return feed;
    }
}
