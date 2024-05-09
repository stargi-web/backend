package com.stargi.backend.iam.application.internal.events;

import com.stargi.backend.iam.application.internal.RoleCommandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
public class ApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    @EventListener
    public void on(ApplicationReadyEvent event){
        var name=event.getApplicationContext().getId();
        LOGGER.info("Starting to seed roles if needed for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        roleCommandService.handle();
        LOGGER.info("Finished seeding roles if needed for {} at {}", name, new Timestamp(System.currentTimeMillis()));

    }
}
