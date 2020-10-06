package io.work.confino.repositories;

import io.work.confino.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobMongoRepository extends MongoRepository<Job, Long> {
}
