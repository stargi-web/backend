package com.stargi.backend.domain.services;

import com.stargi.backend.domain.Responses.DeletedUserResponse;
import com.stargi.backend.domain.Responses.UserCreatedResponse;
import com.stargi.backend.domain.commands.CreateUserCommand;
import com.stargi.backend.domain.commands.DeleteUserCommand;

public interface IUserCommandService {
    UserCreatedResponse handle(CreateUserCommand command);
    DeletedUserResponse handle(DeleteUserCommand command);
}
