package io.work.confino.repositories;

import io.work.confino.models.Role;
import io.work.confino.utils.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleMongoRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
