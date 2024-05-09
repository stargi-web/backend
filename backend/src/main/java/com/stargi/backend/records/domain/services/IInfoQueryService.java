package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.entities.Info;
import com.stargi.backend.records.domain.queries.FindInfoByIdQuery;
import com.stargi.backend.records.domain.queries.FindInfoByNameQuery;

import java.util.List;
import java.util.Optional;

public interface IInfoQueryService {
    Optional<Info> handle(FindInfoByIdQuery query);
    List<Info> handle(FindInfoByNameQuery query);
}
