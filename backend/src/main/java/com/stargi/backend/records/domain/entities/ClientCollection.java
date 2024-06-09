package com.stargi.backend.records.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stargi.backend.iam.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ClientCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String collectionName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "clientCollection")
    @JsonIgnoreProperties("clientCollection")
    private List<Client> clients;

    public ClientCollection(String collectionName, User u){
        this.collectionName=collectionName;
        this.user=u;
        this.clients= new ArrayList<>();
    }
}
