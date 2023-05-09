package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Feed.IFeedService;
import com.sysmap.parrot.application.requests.Post.GetPost.GetPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
@Tag(name = "Feed")
public class FeedController {

    @Autowired
    IFeedService _feedService;

    @GetMapping()
    @Operation(summary = "Get feed - list of posts created by you and the people you follow")
    public ResponseEntity<List<GetPostResponse>> getFeed() {
        var response = _feedService.getFeed();

        return ResponseEntity.ok().body(response);
    }
}
