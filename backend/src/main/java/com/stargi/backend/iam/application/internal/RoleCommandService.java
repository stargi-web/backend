package com.stargi.backend.iam.application.internal;

import com.stargi.backend.iam.domain.entities.Role;
import com.stargi.backend.iam.domain.enums.Roles;
import com.stargi.backend.iam.domain.services.IRoleCommandService;
import com.stargi.backend.iam.infrastructure.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoleCommandService implements IRoleCommandService {
    private final IRoleRepository roleRepository;

    @Override
    public void handle() {
        Arrays.stream(Roles.values()).forEach(role->{
            if(!roleRepository.existsByName(role)){
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
