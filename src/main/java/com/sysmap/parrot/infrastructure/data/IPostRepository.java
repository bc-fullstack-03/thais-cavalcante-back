package com.sysmap.parrot.infrastructure.data;

import com.sysmap.parrot.domain.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
}
