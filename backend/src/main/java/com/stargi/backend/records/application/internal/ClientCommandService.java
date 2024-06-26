package com.stargi.backend.records.application.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.commands.AssignUserToClientCommand;
import com.stargi.backend.records.domain.commands.CreateClientsFromJsonCommand;
import com.stargi.backend.records.domain.commands.EditClientCommand;
import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.enums.Stage;
import com.stargi.backend.records.domain.services.IClientCommandService;
import com.stargi.backend.records.infrastructure.persistence.ClientCollectionRepository;
import com.stargi.backend.records.infrastructure.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientCommandService implements IClientCommandService {

    private final ClientRepository clientRepository;
    private final ClientCollectionRepository clientCollectionRepository;
    private final IUserRepository userRepository;

    public ClientCommandService(ClientRepository clientRepository, ClientCollectionRepository clientCollectionRepository, IUserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.clientCollectionRepository = clientCollectionRepository;

        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateClientsFromJsonCommand command) {
        var collection=this.clientCollectionRepository.findById(command.collectionId());
        if(collection.isEmpty()) return 0L;
        for(JsonNode clientNode: command.clientsJson()){
            Client client=new Client();
            client.setClientCollection(collection.get());
            client.setStage(Stage.NO_CONTACTADO);
            client.setDataInfo(clientNode.toString());
            collection.get().addClient(clientRepository.save(client));
        }

        clientCollectionRepository.save(collection.get());
        return 1L;
    }

    @Override
    public Long handle(AssignUserToClientCommand command) {
        var client=this.clientRepository.findById(command.clientId());
        var user=this.userRepository.findById(command.userId());
        if(client.isEmpty()|| user.isEmpty())return 0L;
        client.get().setUser(user.get());
        user.get().addClient(client.get());
        this.clientRepository.save(client.get());
        this.userRepository.save(user.get());
        return 1L;


    }

    @Override
    public Long handle(EditClientCommand command) {
        var client=this.clientRepository.findById(command.clientId());
        if(client.isEmpty()) return 0L;
        client.get().setMessage(command.newMessage());
        client.get().setStage(Stage.valueOf(command.newStage()));
        this.clientRepository.save(client.get());
        return 1L;
    }
}
