package com.sysmap.parrot.application.requests.Connection;

import com.sysmap.parrot.application.requests.Connection.GetFollowedUser.GetFollowedUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetFollowerUser.GetFollowerUserResponse;
import com.sysmap.parrot.application.requests.Connection.GetUserToConnectResponse.GetUserToConnectResponse;
import com.sysmap.parrot.application.requests.User.IUserService;
import com.sysmap.parrot.domain.embedded.FollowerUser;
import com.sysmap.parrot.domain.embedded.FollowingUser;
import com.sysmap.parrot.domain.entities.Connection;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.exception.forbiddenException.ForbiddenException;
import com.sysmap.parrot.exception.notFoundException.NotFoundException;
import com.sysmap.parrot.infrastructure.data.IConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConnectionService implements IConnectionService {
    @Autowired
    private IConnectionRepository _connectionRepository;

    @Autowired
    private IUserService _userService;

    private User getAuthenticatedUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public String followOrUnfollowUser(String userToFollowId) {
        var authenticatedUser = getAuthenticatedUser();

        if (authenticatedUser.getId().equals(UUID.fromString(userToFollowId))) {
            throw new ForbiddenException("Não é possível seguir seu próprio perfil");
        }

        var authenticatedUserConnection = _connectionRepository.getConnectionByUserId(authenticatedUser.getId()).orElseGet(() -> {
            var connection = new Connection(authenticatedUser.getId());
            _connectionRepository.save(connection);
            return connection;
        });

        var userToFollowConnection = _connectionRepository.getConnectionByUserId(UUID.fromString(userToFollowId)).orElseGet(() -> {
            var connection = new Connection(UUID.fromString(userToFollowId));
            _connectionRepository.save(connection);
            return connection;
        });

        var following = authenticatedUserConnection.getFollowing();
        var followedUser = following.stream().filter(u -> u.getUserId().equals(UUID.fromString(userToFollowId))).findFirst();

        if (followedUser.isPresent()) {
            following.remove(followedUser.get());
            userToFollowConnection.getFollowers().remove(new FollowerUser(authenticatedUser.getId()));

            _connectionRepository.save(authenticatedUserConnection);
            _connectionRepository.save(userToFollowConnection);

            return "Usuário removido da sua rede!";

        } else {
            following.add(new FollowingUser(UUID.fromString(userToFollowId)));
            userToFollowConnection.getFollowers().add(new FollowerUser(authenticatedUser.getId()));

            _connectionRepository.save(authenticatedUserConnection);
            _connectionRepository.save(userToFollowConnection);

            return "Usuário adicionado a sua rede!";
        }
    }

    public List<GetFollowedUserResponse> getFollowingList(String userId) {
        var connection = _connectionRepository.getConnectionByUserId(UUID.fromString(userId)).orElse(null);

        if (connection == null) {
            throw new NotFoundException("Esse usuário ainda não segue ninguém");
        }
        List<GetFollowedUserResponse> followedUserList = new ArrayList<>();

        var followedUsers = connection.getFollowing();

        if (followedUsers.isEmpty()) {
            throw new NotFoundException("Esse usuário ainda não segue ninguém");
        }

        for (FollowingUser followedUser : followedUsers) {
            var followedUserResponse = new GetFollowedUserResponse(
                    followedUser.getUserId()
            );
            followedUserList.add(followedUserResponse);
        }
        return followedUserList;
    }

    public Number followingCount(String userId) {
        var connection = _connectionRepository.getConnectionByUserId(UUID.fromString(userId)).orElse(null);

        if (connection == null) {
            return 0;
        }

        var numberOfFollowedUsers = connection.getFollowing().size();
        return numberOfFollowedUsers;
    }

    public List<GetFollowerUserResponse> getFollowersList(String userId) {
        var connection = _connectionRepository.getConnectionByUserId(UUID.fromString(userId)).orElse(null);

        if (connection == null) {
            throw new NotFoundException("Esse usuário ainda não tem seguidores");
        }
        List<GetFollowerUserResponse> followerUserList = new ArrayList<>();

        var followerUsers = connection.getFollowers();

        if (followerUsers.isEmpty()) {
            throw new NotFoundException("Esse usuário ainda não tem seguidores");
        }

        for (FollowerUser followerUser : followerUsers) {
            var followerUserResponse = new GetFollowerUserResponse(
                    followerUser.getUserId()
            );
            followerUserList.add(followerUserResponse);
        }
        return followerUserList;
    }

    public Number followersCount(String userId) {
        var connection = _connectionRepository.getConnectionByUserId(UUID.fromString(userId)).orElse(null);

        if (connection == null) {
            return 0;
        }

        var numberOfFollowerUsers = connection.getFollowers().size();
        return numberOfFollowerUsers;
    }

    public List<GetUserToConnectResponse> getUsersToConnect() {
        var authenticatedUser = getAuthenticatedUser();

        List<GetUserToConnectResponse> usersToConnect = new ArrayList<>();

        var users = _userService.getAllUsers();


        for (User user : users) {
            if(user.getId() != authenticatedUser.getId()) {
                var userFollowingCount = followingCount(user.getId().toString());
                var userFollowersCount = followersCount(user.getId().toString());
                var userToFollowResponse = new GetUserToConnectResponse(
                        user.getId(),
                        user.getName(),
                        userFollowingCount,
                        userFollowersCount
                );
                usersToConnect.add(userToFollowResponse);
            }
        }

        return usersToConnect;
    }
}