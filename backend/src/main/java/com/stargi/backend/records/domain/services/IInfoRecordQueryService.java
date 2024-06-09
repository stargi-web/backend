package com.stargi.backend.records.domain.services;

import com.stargi.backend.records.domain.entities.InfoRecord;
import com.stargi.backend.records.domain.queries.FindInfoRecordByInfoId;

import java.util.List;

public interface IInfoRecordQueryService {
    List<InfoRecord> handle(FindInfoRecordByInfoId query);
}
