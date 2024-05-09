package com.stargi.backend.records.application.internal;

import com.stargi.backend.iam.application.internal.events.ApplicationReadyEventHandler;
import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.entities.Info;
import com.stargi.backend.records.domain.services.IInfoCommandService;
import com.stargi.backend.records.infrastructure.persistence.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoCommandService implements IInfoCommandService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    private final InfoRepository infoRepository;
    private final IUserRepository userRepository;
    @Override
    public Optional<Info> handle(CreateInfoCommand command) {
        var user=userRepository.findById(command.userId());
        if(user.isEmpty()) throw new RuntimeException("Usuario no existe");
        LOGGER.info(command.toString());
        Info newInfo= infoRepository.save(new Info(command.ruc(), command.businessName(), command.country(), user.get()));
        LOGGER.info(newInfo.toString());
        user.get().addInfo(newInfo);
        userRepository.save(user.get());
        return Optional.of(newInfo);
    }
}
