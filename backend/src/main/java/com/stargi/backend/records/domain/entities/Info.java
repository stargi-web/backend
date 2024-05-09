package com.stargi.backend.records.domain.entities;

import com.stargi.backend.iam.domain.entities.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11)
    private String ruc;

    @Column
    private String businessName;

    @Column
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Info(String ruc,String businessName,String country,User user){
        this.ruc=ruc;
        this.businessName=businessName;
        this.country=country;
        this.user=user;
    }
}
