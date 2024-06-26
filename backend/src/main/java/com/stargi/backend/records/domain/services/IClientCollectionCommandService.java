package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.commands.CreateClientCollectionCommand;

public interface IClientCollectionCommandService {
    Long handle(CreateClientCollectionCommand command);
}
