package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.entities.Info;

import java.util.Optional;

public interface IInfoCommandService {
    Optional<Info> handle(CreateInfoCommand command);
}
