package com.stargi.backend.records.interfaces.rest;

import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.services.IInfoCommandService;
import com.stargi.backend.records.domain.services.IInfoQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/info")
public class InfoController {
    private final IInfoCommandService infoCommandService;
    private final IInfoQueryService infoQueryService;

    public InfoController(IInfoCommandService infoCommandService, IInfoQueryService infoQueryService) {
        this.infoCommandService = infoCommandService;
        this.infoQueryService = infoQueryService;
    }

    @PostMapping()
    public ResponseEntity<?> createInfo(@RequestBody CreateInfoCommand command){
        var response=infoCommandService.handle(command);
        return ResponseEntity.ok(response.get());
    }

}
