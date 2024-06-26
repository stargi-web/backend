package com.stargi.backend.records.infrastructure.persistence;

import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.entities.ClientCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByUser(User user);
    Page<Client> findByClientCollection(ClientCollection clientCollection, Pageable pageable);
    boolean existsByClientCollection(ClientCollection clientCollection);

    @Query("SELECT c FROM Client c WHERE c.user.id = :userId AND c.clientCollection.id = :collectionId")
    List<Client> findByUserAndClientCollection(@Param("userId") Long userId, @Param("collectionId") Long collectionId);

}
