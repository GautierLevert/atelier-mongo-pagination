package com.krgcorporate.mongopagination.repotisory;

import com.krgcorporate.mongopagination.domain.VeryImportantData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeryImportantRepository extends MongoRepository<VeryImportantData, String> {

    long countByProcessed(boolean processed);

    List<VeryImportantData> findAllByProcessed(boolean processed);
}
