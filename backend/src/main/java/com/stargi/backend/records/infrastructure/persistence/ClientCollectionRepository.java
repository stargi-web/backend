package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.records.domain.entities.ClientCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCollectionRepository extends JpaRepository<ClientCollection,Long> {

}
