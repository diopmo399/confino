package io.work.confino.repositories;

import io.work.confino.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "candidates" ,path = "candidate")
public interface CandidateMongoRepository extends MongoRepository<Candidate, Long> {
}
