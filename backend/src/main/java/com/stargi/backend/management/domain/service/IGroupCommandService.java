package com.stargi.backend.management.domain.service;

import com.stargi.backend.management.domain.commands.AddMemberToGroupCommand;
import com.stargi.backend.management.domain.commands.ChangeLeaderGroupCommand;
import com.stargi.backend.management.domain.commands.CreateGroupCommand;
import com.stargi.backend.management.domain.commands.CreateGroupWithoutLeaderCommand;

public interface IGroupCommandService {
    Long handle(CreateGroupCommand command);
    Long handle(AddMemberToGroupCommand command);
    Long handle(ChangeLeaderGroupCommand command);
    Long handle(CreateGroupWithoutLeaderCommand command);
}
