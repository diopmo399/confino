package io.work.confino.repositories;

import io.work.confino.models.CompanyAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyAccountMongoRepository extends MongoRepository<CompanyAccount, String> {
    Optional<CompanyAccount> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}


