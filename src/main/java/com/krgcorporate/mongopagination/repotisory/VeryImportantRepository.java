package com.krgcorporate.mongopagination.repotisory;

import com.krgcorporate.mongopagination.domain.VeryImportantData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface VeryImportantRepository extends MongoRepository<VeryImportantData, String> {

    long countByProcessed(boolean processed);

    @Query(value = "{ processed: false }", sort = "{ createdAt: 1 }")
    Stream<VeryImportantData> findNotProcessed();
}
