package com.stargi.backend.records.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.records.domain.enums.Stage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false)
    private String ruc;

    @Column
    private String businessName;

    @Column
    private String country;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

    @Column
    private String commentary;

    @Column
    private String oppNumber;

    @Column
    private String product;

    @Column
    private Long units;

    @Column
    private Long realRent;

    @Column
    private String contact;

    @Column
    private String contactNumber;

    @Column
    private String email;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationAt;


    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate closeAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sales_manager_id")
    private User salesManager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="executive_id")
    private User executive;

    @OneToMany(mappedBy = "info",fetch =  FetchType.LAZY)
    @JsonManagedReference
    private List<InfoRecord> records;

    public Info(String ruc, String businessName, String country, Stage stage, String commentary, String oppNumber,
                String product, Long units, Long realRent, String contact, String contactNumber, String email,
                LocalDate expirationAt, User user, LocalDate updatedAt, LocalDate closeAt, User salesManager, User executive) {
        this.ruc = ruc;
        this.businessName = businessName;
        this.country = country;
        this.stage = stage;
        this.commentary = commentary;
        this.oppNumber = oppNumber;
        this.product = product;
        this.units = units;
        this.realRent = realRent;
        this.contact = contact;
        this.contactNumber = contactNumber;
        this.email = email;
        this.expirationAt = expirationAt;
        this.user = user;
        this.updatedAt = updatedAt;
        this.closeAt = closeAt;
        this.salesManager = salesManager;
        this.executive = executive;
        this.records=new ArrayList<>();
    }

    public void setInfo(String newStage,String newCommentary,LocalDate newExpirationAt,LocalDate newCloseAt){
        this.stage=Stage.valueOf(newStage);
        this.commentary=newCommentary;
        this.expirationAt=newExpirationAt;
        this.closeAt=newCloseAt;
    }

    public void addRecord(InfoRecord record){
        this.records.add(record);
    }
}
