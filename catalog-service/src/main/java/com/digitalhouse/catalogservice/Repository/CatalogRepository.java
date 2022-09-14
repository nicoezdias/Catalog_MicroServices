package com.digitalhouse.catalogservice.Repository;

import com.digitalhouse.catalogservice.Model.Document.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {

    Optional<Catalog> findByGenre(String genre);
}
