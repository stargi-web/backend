package com.stargi.backend.domain.commands;

import java.time.LocalDate;

public record CreateUserCommand(String emai,String password,String firstName,String lastName, LocalDate birthDate,String rol) {

}
