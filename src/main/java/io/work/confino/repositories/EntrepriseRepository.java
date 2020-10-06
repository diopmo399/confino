package io.work.confino.repositories;

import io.work.confino.models.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "entreprises", path = "entreprises")
public interface EntrepriseRepository extends ElasticsearchRepository<Company, String> {

}
