package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Feed.IFeedService;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
public class FeedController {

    @Autowired
    IFeedService _feedService;

    @GetMapping()
    public ResponseEntity<List<GetPostResponse>> getFeed() {
        var response = _feedService.getFeed();

        return ResponseEntity.ok().body(response);
    }
}
