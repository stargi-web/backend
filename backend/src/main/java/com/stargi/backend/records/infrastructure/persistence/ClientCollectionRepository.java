package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.interfaces.dtos.CollectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCollectionRepository extends JpaRepository<ClientCollection,Long> {
    List<ClientCollection> findByUser(User user);

    @Query("SELECT DISTINCT c.clientCollection FROM Client c WHERE c.user.id = :userId")
    List<ClientCollection> findClientCollectionsByUserId(@Param("userId") Long userId);


}
