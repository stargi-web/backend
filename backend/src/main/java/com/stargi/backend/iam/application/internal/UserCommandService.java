package com.stargi.backend.iam.application.internal;

import java.util.Optional;
import java.util.stream.Collectors;

import com.stargi.backend.iam.domain.commands.EditPasswordCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import com.stargi.backend.iam.application.outbound.hashing.HashingService;
import com.stargi.backend.iam.application.outbound.services.TokenService;
import com.stargi.backend.iam.domain.Responses.DeletedUserResponse;
import com.stargi.backend.iam.domain.Responses.UserCreatedResponse;
import com.stargi.backend.iam.domain.Responses.UserLogedResponse;
import com.stargi.backend.iam.domain.commands.CreateUserCommand;
import com.stargi.backend.iam.domain.commands.DeleteUserCommand;
import com.stargi.backend.iam.domain.commands.LogInUserCommand;
import com.stargi.backend.iam.domain.entities.User;
import com.stargi.backend.iam.domain.services.IUserCommandService;
import com.stargi.backend.iam.infrastructure.IRoleRepository;
import com.stargi.backend.iam.infrastructure.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandService implements IUserCommandService{

    private final IUserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final IRoleRepository roleRepository;
    @Override
    public UserCreatedResponse handle(CreateUserCommand command) {
        if(userRepository.existsByUserName(command.userName())){
            throw new RuntimeException("Una cuenta con el nombre de usuario ya existe");
        }
        var roles= command
        .roles()
        .stream()
        .map(role->roleRepository.findByName(role.getNameAsRoles()).orElseThrow())
        .collect(Collectors.toList());
        var newUser=new User(command.userName(), hashingService.encode(command.password()), command.firstName(), command.lastName(), command.birthDate(),roles);
        var savedUser=userRepository.save(newUser);
        return new UserCreatedResponse(savedUser.getId(), savedUser.getFirstName(), savedUser.getUsername());
    }

    @Override
    public Optional<UserLogedResponse> handle(LogInUserCommand command) {
        var user=userRepository.findByUserName(command.userName());
        if(user.isEmpty()) throw new RuntimeException("Usuario no existe");
        if(!hashingService.matches(command.password(), user.get().getPassword())) throw new RuntimeException("Contraseña inválida");
        var token= tokenService.generateTokenWithIdAndRoles(user.get().getUsername(),user.get().getId(),user.get().getRoles());
        return Optional.of(new UserLogedResponse(user.get().getId(),user.get().getUsername(),token));
    }

    @Override
    public DeletedUserResponse handle(DeleteUserCommand command) {
        var user=userRepository.findById(command.userId());
        if(user.isEmpty()) throw new RuntimeException("Usuario no existe");
        DeletedUserResponse response=new DeletedUserResponse(user.get().getFirstName());
        userRepository.delete(user.get());
        return response;

    }

    @Override
    public User handle(EditPasswordCommand command) {
        var user=userRepository.findById(command.userId());
        if(user.isEmpty()) throw new RuntimeException("Usuario no existe");
        user.get().setPassword(hashingService.encode(command.newPassword()));
        userRepository.save(user.get());
        return user.get();
    }

}
