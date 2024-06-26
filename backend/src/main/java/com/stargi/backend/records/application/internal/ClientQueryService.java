package com.stargi.backend.records.application.internal;

import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.queries.FindClientsByCollectionQuery;
import com.stargi.backend.records.domain.queries.FindClientsByUserAndCollectionIdQuery;
import com.stargi.backend.records.domain.queries.FindClientsCollectionNameByUserId;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;
import com.stargi.backend.records.domain.services.IClientQueryService;
import com.stargi.backend.records.infrastructure.persistence.ClientCollectionRepository;
import com.stargi.backend.records.infrastructure.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientQueryService implements IClientQueryService {
    private final ClientRepository clientRepository;
    private final ClientCollectionRepository clientCollectionRepository;

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return this.clientRepository.findAll();
    }

    @Override
    public List<ClientCollection> handle(FindClientsCollectionNameByUserId query) {
        List<ClientCollection> response=this.clientCollectionRepository.findClientCollectionsByUserId(query.userId());

        return response;
    }

    @Override
    public List<Client> handle(FindClientsByUserAndCollectionIdQuery query) {
        List<Client> response=this.clientRepository.findByUserAndClientCollection(query.userId(), query.collectionId());
        return response;
    }

    @Override
    public Page<Client> handle(FindClientsByCollectionQuery query) {
        var clientCollection=this.clientCollectionRepository.findById(query.collectionId());
        if(clientCollection.isEmpty()) throw new RuntimeException("La colección no existe");
        return this.clientRepository.findByClientCollection(clientCollection.get(), query.pageable());
    }
}
