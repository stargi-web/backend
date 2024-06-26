package com.stargi.backend.records.interfaces.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stargi.backend.records.application.internal.ClientCommandService;
import com.stargi.backend.records.application.internal.ClientQueryService;
import com.stargi.backend.records.domain.commands.AssignUserToClientCommand;
import com.stargi.backend.records.domain.commands.CreateClientsFromJsonCommand;
import com.stargi.backend.records.domain.commands.EditClientCommand;
import com.stargi.backend.records.domain.queries.FindClientsByCollectionQuery;
import com.stargi.backend.records.domain.queries.FindClientsByUserAndCollectionIdQuery;
import com.stargi.backend.records.domain.queries.FindClientsCollectionNameByUserId;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;
import com.stargi.backend.records.interfaces.dtos.ClientDTO;
import com.stargi.backend.records.interfaces.transform.FromClientToDTO;
import com.stargi.backend.records.interfaces.transform.FromCollectionToCollectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/client",produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private final ClientQueryService clientQueryService;
    private final ClientCommandService clientCommandService;
    public ClientController(ClientQueryService clientQueryService, ClientCommandService clientCommandService) {
        this.clientQueryService = clientQueryService;
        this.clientCommandService = clientCommandService;
    }

    @GetMapping
    public ResponseEntity<?> getAllClients(){
        var query=new GetAllClientsQuery();
        return ResponseEntity.ok(this.clientQueryService.handle(query));
    }
    @GetMapping("{userId}/user/collection")
    public ResponseEntity<?> getCollectionNameByUserId(@PathVariable("userId")Long userId){
        var query=new FindClientsCollectionNameByUserId(userId);
        var response=this.clientQueryService.handle(query);
        var newResponse=response.stream().map(FromCollectionToCollectionDTO::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(newResponse);
    }
    @GetMapping("{userId}/user/{collectionId}/collection")
    public ResponseEntity<?> getClientsByUserAndCollection(@PathVariable("userId")Long userId,@PathVariable("collectionId")Long collectionId){
        var query= new FindClientsByUserAndCollectionIdQuery(userId,collectionId);
        var response=this.clientQueryService.handle(query);
        return ResponseEntity.ok(response);
    }
    @GetMapping("{collectionId}/collection")
    public ResponseEntity<?> getClientsByCollection(@PathVariable("collectionId")Long collectionId,
                                                    @RequestParam(defaultValue = "0")int page,
                                                    @RequestParam(defaultValue = "10")int size,
                                                    @RequestParam(defaultValue = "id")String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
            var query=new FindClientsByCollectionQuery(collectionId,pageable);
            var response=this.clientQueryService.handle(query);
            return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<?> createClients(@RequestParam("collectionId") String collectionId, @RequestParam("jsonFile") MultipartFile jsonFile){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile.getBytes());
            CreateClientsFromJsonCommand command = new CreateClientsFromJsonCommand(Long.parseLong(collectionId), jsonNode);
            var response = this.clientCommandService.handle(command);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo JSON");
        }
    }

    @PutMapping("{userId}/user/{clientId}/client")
    public ResponseEntity<?> assignClientToUser(@PathVariable("userId")Long userId,@PathVariable("clientId")Long clientId){
        var command=new AssignUserToClientCommand(userId,clientId);
        var response=this.clientCommandService.handle(command);
        if(response==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);

    }

    @PutMapping("edit")
    public ResponseEntity<?> editClient(@RequestBody EditClientCommand command){
        var response=this.clientCommandService.handle(command);
        if(response==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
