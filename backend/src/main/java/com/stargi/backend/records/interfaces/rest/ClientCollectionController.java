package com.stargi.backend.records.interfaces.rest;

import com.stargi.backend.records.application.internal.ClientCollectionCommandService;
import com.stargi.backend.records.application.internal.ClientCollectionQueryService;
import com.stargi.backend.records.domain.commands.CreateClientCollectionCommand;
import com.stargi.backend.records.domain.queries.FindClientCollectionByCreatorId;
import com.stargi.backend.records.domain.queries.IfCollectionHaveClientQuery;
import com.stargi.backend.records.interfaces.transform.FromCollectionToCollectionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clientCollection",produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientCollectionController {
    private final ClientCollectionCommandService clientCollectionCommandService;
    private final ClientCollectionQueryService clientCollectionQueryService;

    public ClientCollectionController(ClientCollectionCommandService clientCollectionCommandService, ClientCollectionQueryService clientCollectionQueryService) {
        this.clientCollectionCommandService = clientCollectionCommandService;
        this.clientCollectionQueryService = clientCollectionQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createClientCollection(@RequestBody CreateClientCollectionCommand command){
        var response=this.clientCollectionCommandService.handle(command);
        if(response==1){
            return ResponseEntity.ok("Exitoso, código de respuesta:"+response);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{userId}/user")
    public ResponseEntity<?> getCollectionByCreatorId(@PathVariable("userId")Long userId){
        var query=new FindClientCollectionByCreatorId(userId);
        var response=this.clientCollectionQueryService.handle(query).stream().map(FromCollectionToCollectionDTO::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{collectionId}/has-clients")
    public ResponseEntity<?> hasClients(@PathVariable("collectionId")Long collectionId){
        var query=new IfCollectionHaveClientQuery(collectionId);
        var response=this.clientCollectionQueryService.handle(query);
        return ResponseEntity.ok(response);
    }
}
