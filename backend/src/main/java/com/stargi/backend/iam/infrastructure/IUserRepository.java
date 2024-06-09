package com.stargi.backend.iam.infrastructure;

import com.stargi.backend.management.domain.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stargi.backend.iam.domain.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);
    List<User> findByTeam(Team team);

    @Query("SELECT t.id FROM Team t WHERE t.leader.id = :userId")
    Long findTeamIdByLeaderId(@Param("userId") Long userId);
}
