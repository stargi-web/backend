package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.entities.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {
    List<Info> findByUser(User user);
    List<Info> findByUserId(Long id);

    @Query("SELECT i FROM Info i WHERE i.user.team.id = :teamId")
    List<Info> findInfosByTeamId(@Param("teamId") Long teamId);
}
