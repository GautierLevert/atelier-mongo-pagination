package com.krgcorporate.mongopagination.repotisory;

import com.krgcorporate.mongopagination.domain.VeryImportantData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VeryImportantRepository extends MongoRepository<VeryImportantData, String> {

    long countByProcessed(boolean processed);

    @Query(value = "{ processed: false }", sort = "{ createdAt: 1 }")
    Page<VeryImportantData> findNotProcessed(Pageable pageable);
}
