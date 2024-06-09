package com.stargi.backend.iam.domain.entities;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stargi.backend.management.domain.entities.Team;
import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.domain.entities.Info;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class User implements UserDetails{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name="password",nullable=false)
    @Setter
    private String password;

    @Getter
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Getter
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Getter
    @Column(name="birth_date",nullable = false)
    private LocalDate birthDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Info> infos;

    @Getter
    @Setter
    @OneToOne(mappedBy = "leader",fetch = FetchType.EAGER)
    @JsonBackReference
    private Team leadingTeam;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "user")
    private List<ClientCollection> clientCollections;

    @OneToMany(mappedBy = "user")
    private List<Client> clients;

    public void addInfo(Info info){
        this.infos.add(info);
    }
    public User(){
        this.roles=new HashSet<>();
        this.infos=new ArrayList<>();
        this.clientCollections=new ArrayList<>();
        this.clients=new ArrayList<>();
    }
    public void addClient(Client c){
        this.clients.add(c);
    }
    public void makeLeader(Team leadingTeam){
        this.leadingTeam = leadingTeam;
    }
    public void addToGroup(Team team){
        this.team = team;
    }

    public void addCollectionClient(ClientCollection c){
        this.clientCollections.add(c);
    }
    public User(String username,String password,String firstName,String lastName,LocalDate birthDate,List<Role> roles){
        this();
        this.userName=username;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDate=birthDate;
        this.addRoles(roles);
    }
    void setEmail(String newUsername){
        this.userName=newUsername;
    }
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public void addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
    }

    public List<String> getRoles(){
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }



    @Override
    public String getUsername() {
        return this.userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
