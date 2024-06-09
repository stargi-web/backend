package com.stargi.backend.records.application.internal;

import com.stargi.backend.records.domain.entities.InfoRecord;
import com.stargi.backend.records.domain.queries.FindInfoRecordByInfoId;
import com.stargi.backend.records.domain.services.IInfoRecordQueryService;
import com.stargi.backend.records.infrastructure.persistence.InfoRecordRepository;
import com.stargi.backend.records.infrastructure.persistence.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoRecordQueryService implements IInfoRecordQueryService {

    private final InfoRecordRepository infoRecordRepository;
    private final InfoRepository infoRepository;
    @Override
    public List<InfoRecord> handle(FindInfoRecordByInfoId query) {
        var info=this.infoRepository.findById(query.infoId());
        if(info.isEmpty()) throw new RuntimeException("Info no encontrado");

        return this.infoRecordRepository.findByInfo(info.get());
    }
}
