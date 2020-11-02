package io.work.confino.repositories;

import io.work.confino.models.CompanyAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyAccountMongoRepository extends MongoRepository<CompanyAccount, String> {
}
