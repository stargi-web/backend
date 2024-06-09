package com.stargi.backend.management.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stargi.backend.iam.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="leader_id",unique = true)
    private User leader;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<User> members;

    @Column
    private String name;

    public Team(){
        this.members=new HashSet<>();
    }

    public Team(String name, User leader){
        this.name=name;
        this.leader=leader;

    }
    public Team(String name){
        this.name=name;
    }

    public void addMember(User user){
        this.members.add(user);
    }
}
