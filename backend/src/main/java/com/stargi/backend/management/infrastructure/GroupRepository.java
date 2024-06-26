package com.stargi.backend.management.infrastructure;

import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.management.domain.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Team,Long> {



    Optional<Team> findByLeader(User leader);
}
