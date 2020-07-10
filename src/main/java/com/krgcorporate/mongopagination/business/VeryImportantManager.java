package com.krgcorporate.mongopagination.business;

import com.codepoetics.protonpack.StreamUtils;
import com.krgcorporate.mongopagination.domain.VeryImportantData;
import com.krgcorporate.mongopagination.repotisory.VeryImportantRepository;
import com.krgcorporate.mongopagination.result.Status;
import com.mongodb.client.result.UpdateResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @Autowired)
public class VeryImportantManager {

    private final @NonNull Random rnd = new Random();

    private final @NonNull MongoTemplate template;

    private final @NonNull VeryImportantRepository repository;

    private VeryImportantData generate() {
        return VeryImportantData.builder()
                .id(null)
                .createdAt(Instant.now())
                .processedAt(null)
                .processed(false)
                .value(rnd.nextDouble())
                .build();
    }

    private VeryImportantData process(final VeryImportantData data) {
        return data.withProcessed(true)
                .withProcessedAt(Instant.now());
    }

    public long insert(int count) {
        return IntStream.range(0, count)
                .mapToObj(index -> generate())
                .peek(repository::insert)
                .count();
    }

    public UpdateResult reset() {
        return template.updateMulti(
                new Query(where("processed").is(true)),
                new Update()
                        .set("processed", false)
                        .set("updatedAt", null),
                VeryImportantData.class
        );
    }

    public Status getStatus() {
        return Status.builder()
                .processed(repository.countByProcessed(true))
                .total(repository.count())
                .build();
    }

    public int process() {
        log.info("Start processing {} objects.", repository.countByProcessed(false));

        int count;

        try (Stream<VeryImportantData> stream = repository.findNotProcessed()) {

            count = StreamUtils.zipWithIndex(
                    StreamUtils.windowed(stream.map(this::process), 1000, 1000, true)
            )
                    .peek(pack -> log.info("Page {} retrieved for db.", pack.getIndex()))
                    .peek(pack -> repository.saveAll(pack.getValue()))
                    .peek(pack -> log.info("Page {} processed.", pack.getIndex()))
                    .mapToInt(pack -> pack.getValue().size())
                    .sum();
        }

        log.info("Elements saved.");

        return count;
    }
}
