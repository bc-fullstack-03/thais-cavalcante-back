package com.sysmap.parrot.infrastructure.data;

import com.sysmap.parrot.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
}
