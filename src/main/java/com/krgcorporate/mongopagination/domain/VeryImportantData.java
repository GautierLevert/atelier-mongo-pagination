package com.krgcorporate.mongopagination.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Document
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@JsonCreator, @PersistenceConstructor})
public class VeryImportantData {

    @Id
    private @Nullable String id;

    @Indexed
    private final @NonNull Instant createdAt;

    @Indexed
    private @Nullable Instant updatedAt;

    @Indexed
    private boolean processed;

    private double value;
}
