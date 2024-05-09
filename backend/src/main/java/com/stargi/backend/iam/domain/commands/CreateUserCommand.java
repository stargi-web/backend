package com.stargi.backend.iam.domain.commands;

import java.time.LocalDate;
import java.util.List;

import com.stargi.backend.iam.domain.entities.Role;

public record CreateUserCommand(String userName,String password,String firstName,String lastName, LocalDate birthDate,List<Role> roles) {

}
