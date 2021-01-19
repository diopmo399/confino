package io.work.confino.repositories;

import io.work.confino.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateMongoRepository extends MongoRepository<Candidate, String> {
}
