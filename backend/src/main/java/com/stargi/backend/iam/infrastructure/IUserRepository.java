package com.stargi.backend.iam.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stargi.backend.iam.domain.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>{

}
