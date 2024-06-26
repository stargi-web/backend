package com.stargi.backend.records.application.internal;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.commands.CreateClientCollectionCommand;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.services.IClientCollectionCommandService;
import com.stargi.backend.records.infrastructure.persistence.ClientCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCollectionCommandService implements IClientCollectionCommandService {

    private final ClientCollectionRepository clientCollectionRepository;
    private final IUserRepository userRepository;

    @Override
    public Long handle(CreateClientCollectionCommand command) {
        var user=this.userRepository.findById(command.userId());
        if(user.isEmpty()) return 0L;
        var newCollection=new ClientCollection(command.title(), user.get());
        user.get().addCollectionClient(this.clientCollectionRepository.save(newCollection));
        this.userRepository.save(user.get());
        return 1L;
    }
}
