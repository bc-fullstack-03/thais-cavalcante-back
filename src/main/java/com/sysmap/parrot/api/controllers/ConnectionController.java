package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Connection.GetFollowedUser.GetFollowedUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetFollowerUser.GetFollowerUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetUserToConnectResponse.GetUserToConnectResponse;
import com.sysmap.parrot.application.requests.Connection.IConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/connection")
public class ConnectionController {

    @Autowired
    private IConnectionService _connectionService;

    @PostMapping("following/{userToFollowId}")
    public ResponseEntity<String> followOrUnfollowUser(@PathVariable String userToFollowId) {
        var response = _connectionService.followOrUnfollowUser(userToFollowId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<GetFollowedUserResponse>> getFollowingList(@PathVariable String userId) {
        var response = _connectionService.getFollowingList(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/following/{userId}/count")
    public ResponseEntity<Number> followingCount(@PathVariable String userId) {
        var response = _connectionService.followingCount(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<GetFollowerUserResponse>> getFollowersList(@PathVariable String userId) {
        var response = _connectionService.getFollowersList(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/followers/{userId}/count")
    public ResponseEntity<Number> followersCount(@PathVariable String userId) {
        var response = _connectionService.followersCount(userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    public ResponseEntity<List<GetUserToConnectResponse>> getUsersToConnect() {
        var response = _connectionService.getUsersToConnect();

        return ResponseEntity.ok().body(response);
    }
}
