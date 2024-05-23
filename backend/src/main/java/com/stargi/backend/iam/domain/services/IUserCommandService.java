package com.stargi.backend.iam.domain.services;
import com.stargi.backend.iam.domain.commands.EditPasswordCommand;
import com.stargi.backend.iam.domain.entities.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.stargi.backend.iam.domain.Responses.DeletedUserResponse;
import com.stargi.backend.iam.domain.Responses.UserCreatedResponse;
import com.stargi.backend.iam.domain.Responses.UserLogedResponse;
import com.stargi.backend.iam.domain.commands.CreateUserCommand;
import com.stargi.backend.iam.domain.commands.DeleteUserCommand;
import com.stargi.backend.iam.domain.commands.LogInUserCommand;

import java.util.Optional;

public interface IUserCommandService {
    UserCreatedResponse handle(CreateUserCommand command);
    Optional<UserLogedResponse> handle(LogInUserCommand command);
    DeletedUserResponse handle(DeleteUserCommand command);
    User handle(EditPasswordCommand command);
}
