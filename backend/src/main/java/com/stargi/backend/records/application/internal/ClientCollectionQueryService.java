package com.stargi.backend.records.application.internal;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.queries.FindClientCollectionByCreatorId;
import com.stargi.backend.records.domain.queries.IfCollectionHaveClientQuery;
import com.stargi.backend.records.domain.services.IClientCollectionQueryService;
import com.stargi.backend.records.infrastructure.persistence.ClientCollectionRepository;
import com.stargi.backend.records.infrastructure.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCollectionQueryService implements IClientCollectionQueryService {

    private final ClientCollectionRepository clientCollectionRepository;
    private final IUserRepository userRepository;
    private final ClientRepository clientRepository;
    @Override
    public List<ClientCollection> handle(FindClientCollectionByCreatorId query) {
        var user=this.userRepository.findById(query.userId());
        if(user.isEmpty()) return List.of();
        return this.clientCollectionRepository.findByUser(user.get());
    }

    @Override
    public boolean handle(IfCollectionHaveClientQuery query) {
        var collection=this.clientCollectionRepository.findById(query.collectionId());
        if(collection.isEmpty())throw new RuntimeException("La colección no existe");
        return clientRepository.existsByClientCollection(collection.get());
    }
}
