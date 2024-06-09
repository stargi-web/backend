package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.records.domain.entities.Info;
import com.stargi.backend.records.domain.entities.InfoRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRecordRepository extends JpaRepository<InfoRecord,Long> {
    List<InfoRecord> findByInfo(Info info);
}
