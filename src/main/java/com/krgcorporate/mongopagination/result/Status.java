package com.krgcorporate.mongopagination.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @JsonCreator)
public class Status {

    private long total;

    private long processed;
}
