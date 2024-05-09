package com.stargi.backend.iam.domain.entities;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.stargi.backend.records.domain.entities.Info;
import jakarta.persistence.*;
import lombok.Getter;
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

    public void addInfo(Info info){
        this.infos.add(info);
    }
    public User(){
        this.roles=new HashSet<>();
        this.infos=new ArrayList<>();
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

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }

    
}
