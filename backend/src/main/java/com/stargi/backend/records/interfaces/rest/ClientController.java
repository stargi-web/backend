package com.stargi.backend.records.interfaces.rest;

import com.stargi.backend.records.application.internal.ClientQueryService;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/client",produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private final ClientQueryService clientQueryService;

    public ClientController(ClientQueryService clientQueryService) {
        this.clientQueryService = clientQueryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllClients(){
        var query=new GetAllClientsQuery();
        return ResponseEntity.ok(this.clientQueryService.handle(query));
    }
}
