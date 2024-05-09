package com.stargi.backend.records.application.internal;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.entities.Info;
import com.stargi.backend.records.domain.queries.FindInfoByIdQuery;
import com.stargi.backend.records.domain.queries.FindInfoByNameQuery;
import com.stargi.backend.records.domain.services.IInfoQueryService;
import com.stargi.backend.records.infrastructure.persistence.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InfoQueryService implements IInfoQueryService {

    private final IUserRepository userRepository;
    private final InfoRepository infoRepository;

    @Override
    public Optional<Info> handle(FindInfoByIdQuery query) {
        return infoRepository.findById(query.id());
    }

    @Override
    public List<Info> handle(FindInfoByNameQuery query) {
        var user=userRepository.findByUserName(query.name());
        if(user.isEmpty()) throw new RuntimeException("Usuario no encontrado");
        return infoRepository.findByUser(user.get());
    }
}
