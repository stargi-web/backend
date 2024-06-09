package com.stargi.backend.records.application.internal;

import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;
import com.stargi.backend.records.domain.services.IClientQueryService;
import com.stargi.backend.records.infrastructure.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientQueryService implements IClientQueryService {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return this.clientRepository.findAll();
    }
}
