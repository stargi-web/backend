package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.queries.GetAllClientsQuery;

import java.util.List;

public interface IClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
}
