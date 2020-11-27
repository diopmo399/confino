package io.work.confino.repositories;

import io.work.confino.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User , String> {
}
