package com.stargi.backend.iam.domain.entities;

import java.util.List;

import com.stargi.backend.iam.domain.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role(Roles name){
        this.name=name;
    }
    /*Get a default role*/
    public static Role getDefaultRole(){
        return new Role(Roles.USER);
    }

    public String getName(){
        return this.name.name();
    }

    /* Get a role from a string*/
    public static Role toRoleFromName(String name){
        return new Role(Roles.valueOf(name));
    }
    /**
     * Validate the role set if it is null or empty
     * @param roles Role set
     * @return Role set
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}
