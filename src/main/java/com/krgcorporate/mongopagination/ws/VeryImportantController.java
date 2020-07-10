package com.krgcorporate.mongopagination.ws;

import com.krgcorporate.mongopagination.business.VeryImportantManager;
import com.krgcorporate.mongopagination.command.InsertCommand;
import com.krgcorporate.mongopagination.result.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @Autowired)
public class VeryImportantController {

    private final @NonNull VeryImportantManager manager;

    @PostMapping("insertion")
    public long insert(@RequestBody final @NonNull InsertCommand command) {
        return manager.insert(command.getCount());
    }

    @GetMapping("status")
    public Status getStatus() {
        return manager.getStatus();
    }
}
