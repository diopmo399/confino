package io.work.confino.repositories;

import io.work.confino.models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyMongoRepository extends MongoRepository<Company, Long> {
}
