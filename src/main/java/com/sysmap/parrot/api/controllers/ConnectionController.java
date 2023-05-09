package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Connection.GetFollowedUser.GetFollowedUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetFollowerUser.GetFollowerUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetUserToConnectResponse.GetUserToConnectResponse;
import com.sysmap.parrot.application.requests.Connection.IConnectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/connection")
@Tag(name = "Connection")
public class ConnectionController {

    @Autowired
    private IConnectionService _connectionService;

    @PostMapping("following/{userToFollowId}")
    @Operation(summary = "Follow/unfollow user")
    public ResponseEntity<String> followOrUnfollowUser(@PathVariable String userToFollowId) {
        var response = _connectionService.followOrUnfollowUser(userToFollowId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/following/{userId}")
    @Operation(summary = "Get the list of people a user follows")
    public ResponseEntity<List<GetFollowedUserResponse>> getFollowingList(@PathVariable String userId) {
        var response = _connectionService.getFollowingList(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/following/{userId}/count")
    @Operation(summary = "Get the amount of people a user follows")
    public ResponseEntity<Number> followingCount(@PathVariable String userId) {
        var response = _connectionService.followingCount(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/followers/{userId}")
    @Operation(summary = "Get the list of people who follow a user")
    public ResponseEntity<List<GetFollowerUserResponse>> getFollowersList(@PathVariable String userId) {
        var response = _connectionService.getFollowersList(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/followers/{userId}/count")
    @Operation(summary = "Get the amount of people who follow a user")
    public ResponseEntity<Number> followersCount(@PathVariable String userId) {
        var response = _connectionService.followersCount(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    @Operation(summary = "Get all the other users in Parrot")
    public ResponseEntity<List<GetUserToConnectResponse>> getUsersToConnect() {
        var response = _connectionService.getUsersToConnect();

        return ResponseEntity.ok().body(response);
    }
}
