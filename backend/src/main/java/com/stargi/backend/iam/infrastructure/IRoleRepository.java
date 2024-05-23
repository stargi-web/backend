package com.stargi.backend.iam.infrastructure;

import java.util.Optional;

import com.stargi.backend.iam.domain.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stargi.backend.iam.domain.entities.Role;


@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles name);
}
