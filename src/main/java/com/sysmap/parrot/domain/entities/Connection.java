package com.sysmap.parrot.domain.entities;

import com.sysmap.parrot.domain.embedded.FollowerUser;
import com.sysmap.parrot.domain.embedded.FollowingUser;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Connection {
    @Id
    private UUID userId;
    private List<FollowingUser> following;
    private List<FollowerUser> followers;

    public Connection(UUID userId) {
        this.userId = userId;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }
}
