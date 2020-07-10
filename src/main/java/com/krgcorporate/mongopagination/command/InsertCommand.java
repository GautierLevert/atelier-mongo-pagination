package com.krgcorporate.mongopagination.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Data
public class InsertCommand {

    private final int count;

    @JsonCreator
    InsertCommand(@Nullable Integer count) {
        this.count = Optional.ofNullable(count)
                .orElse(10000);
    }
}
