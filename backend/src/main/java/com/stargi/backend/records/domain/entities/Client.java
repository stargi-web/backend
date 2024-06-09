package com.stargi.backend.records.domain.entities;

import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.enums.Stage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Stage stage;

    @Getter
    @Setter
    @Column(nullable = false,columnDefinition = "json")
    private String dataInfo;

    @Getter
    @ManyToOne
    @JoinColumn(name = "client_collection_id")
    private ClientCollection clientCollection;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Client(){

    }
    public Client(String stage,String dataInfo,ClientCollection cc){
        this.stage=Stage.valueOf(stage);
        this.dataInfo=dataInfo;
        this.clientCollection=cc;
    }

}
