package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.commands.AssignUserToClientCommand;
import com.stargi.backend.records.domain.commands.CreateClientsFromJsonCommand;
import com.stargi.backend.records.domain.commands.EditClientCommand;

public interface IClientCommandService {
    Long handle(CreateClientsFromJsonCommand command);
    Long handle(AssignUserToClientCommand command);
    Long handle(EditClientCommand command);
}
