package com.stargi.backend.management.infrastructure;

import com.stargi.backend.management.domain.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Team,Long> {
}
