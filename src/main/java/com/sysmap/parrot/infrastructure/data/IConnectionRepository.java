package com.sysmap.parrot.infrastructure.data;

import com.sysmap.parrot.domain.entities.Connection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IConnectionRepository extends MongoRepository<Connection, UUID> {
    Optional<Connection> getConnectionByUserId(UUID userId);
}
