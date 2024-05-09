package com.stargi.backend.iam.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stargi.backend.iam.domain.entities.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);
}
