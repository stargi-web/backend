package com.stargi.backend.iam.domain.services;

import com.stargi.backend.iam.domain.Responses.DeletedUserResponse;
import com.stargi.backend.iam.domain.Responses.UserCreatedResponse;
import com.stargi.backend.iam.domain.commands.CreateUserCommand;
import com.stargi.backend.iam.domain.commands.DeleteUserCommand;

public interface IUserCommandService {
    UserCreatedResponse handle(CreateUserCommand command);
    DeletedUserResponse handle(DeleteUserCommand command);
}
