package com.sysmap.parrot.application.requests.Feed;

import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;

import java.util.List;

public interface IFeedService {
    List<GetPostResponse> getFeed();
}
