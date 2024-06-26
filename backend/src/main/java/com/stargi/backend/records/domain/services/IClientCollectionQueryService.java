package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.queries.FindClientCollectionByCreatorId;
import com.stargi.backend.records.domain.queries.IfCollectionHaveClientQuery;

import java.util.List;

public interface IClientCollectionQueryService {

    List<ClientCollection> handle(FindClientCollectionByCreatorId query);
    boolean handle(IfCollectionHaveClientQuery query);
}
