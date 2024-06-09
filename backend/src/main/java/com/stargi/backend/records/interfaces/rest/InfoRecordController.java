package com.stargi.backend.records.interfaces.rest;

import com.stargi.backend.records.domain.queries.FindInfoRecordByInfoId;
import com.stargi.backend.records.domain.services.IInfoRecordQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/infoRecord",produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoRecordController {
    private final IInfoRecordQueryService infoRecordQueryService;

    public InfoRecordController(IInfoRecordQueryService infoRecordQueryService) {
        this.infoRecordQueryService = infoRecordQueryService;
    }

    @GetMapping("{infoId}")
    public ResponseEntity<?> getInfoRecordByInfoId(@PathVariable("infoId") Long infoId){
        var query= new FindInfoRecordByInfoId(infoId);
        var response= this.infoRecordQueryService.handle(query);
        return ResponseEntity.ok(response);
    }
}
