package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.records.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {


}
