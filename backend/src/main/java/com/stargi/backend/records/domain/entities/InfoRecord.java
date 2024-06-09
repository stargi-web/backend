package com.stargi.backend.records.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stargi.backend.records.domain.enums.Stage;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class InfoRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column
    private LocalDate createdAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info_id")
    @JsonBackReference
    private Info info;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Stage stage;


    @Column
    private String commentary;

    public InfoRecord(){}
    public InfoRecord(LocalDate createdAt,Info info,String stage,String commentary){
        this.createdAt=createdAt;
        this.info=info;
        this.stage=Stage.valueOf(stage);
        this.commentary=commentary;
    }
}
