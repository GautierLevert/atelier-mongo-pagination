package com.krgcorporate.mongopagination.repotisory;

import com.krgcorporate.mongopagination.domain.VeryImportantData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeryImportantRepository extends MongoRepository<VeryImportantData, String> {

    long countByProcessed(boolean processed);
}
