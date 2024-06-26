package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.queries.FindClientsByCollectionQuery;
import com.stargi.backend.records.domain.queries.FindClientsByUserAndCollectionIdQuery;
import com.stargi.backend.records.domain.queries.FindClientsCollectionNameByUserId;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;
import com.stargi.backend.records.interfaces.dtos.CollectionDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
    List<ClientCollection> handle(FindClientsCollectionNameByUserId query);
    List<Client> handle(FindClientsByUserAndCollectionIdQuery query);
    Page<Client> handle(FindClientsByCollectionQuery query);
}
