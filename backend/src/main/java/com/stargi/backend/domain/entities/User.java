package com.stargi.backend.domain.entities;

import java.time.LocalDate;

import com.stargi.backend.domain.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name = "first_name",nullable = false)
    private String firstName;
    
    @Column(name = "last_name",nullable = false)
    private String lastName;
    
    @Column(name="birth_date",nullable = false)
    private LocalDate birthDate;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    void setEmail(String newEmail){
        this.email=newEmail;
    }
}
