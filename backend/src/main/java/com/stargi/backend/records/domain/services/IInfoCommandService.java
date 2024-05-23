package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.commands.DeleteInfoByIdCommand;
import com.stargi.backend.records.domain.commands.EditInfoCommand;
import com.stargi.backend.records.domain.entities.Info;

import java.util.Optional;

public interface IInfoCommandService {
    Optional<Info> handle(CreateInfoCommand command);
    Long handle(DeleteInfoByIdCommand command);
    Optional<Info> handle(EditInfoCommand command);
}
