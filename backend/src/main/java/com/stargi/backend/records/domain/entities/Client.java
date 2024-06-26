package com.stargi.backend.records.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.enums.Stage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private Stage stage;

    @Setter
    @Column(nullable = false,columnDefinition = "json")
    private String dataInfo;

    @JsonIgnore
    @Setter
    @ManyToOne
    @JoinColumn(name = "client_collection_id")
    private ClientCollection clientCollection;

    @Setter
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Getter
    @Setter
    @Column
    private String message;

    public Client(){

    }
    public Client(String stage,String dataInfo,ClientCollection cc){
        this.stage=Stage.valueOf(stage);
        this.dataInfo=dataInfo;
        this.clientCollection=cc;
    }

}
