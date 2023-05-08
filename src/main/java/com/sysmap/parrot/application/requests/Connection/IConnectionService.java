package com.sysmap.parrot.application.requests.Connection;

import com.sysmap.parrot.application.requests.Connection.GetFollowedUser.GetFollowedUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetFollowerUser.GetFollowerUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetUserToConnectResponse.GetUserToConnectResponse;

import java.util.List;

public interface IConnectionService {
    String followOrUnfollowUser(String userToFollowId);
    List<GetFollowedUserResponse> getFollowingList(String userId);
    Number followingCount(String userId);
    List<GetFollowerUserResponse> getFollowersList(String userId);
    Number followersCount(String userId);
    List<GetUserToConnectResponse> getUsersToConnect();
}
