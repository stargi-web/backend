package com.stargi.backend.management.application.internal;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.management.domain.commands.AddMemberToGroupCommand;
import com.stargi.backend.management.domain.commands.ChangeLeaderGroupCommand;
import com.stargi.backend.management.domain.commands.CreateGroupCommand;
import com.stargi.backend.management.domain.commands.CreateGroupWithoutLeaderCommand;
import com.stargi.backend.management.domain.entities.Team;
import com.stargi.backend.management.domain.service.IGroupCommandService;
import com.stargi.backend.management.infrastructure.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCommandService implements IGroupCommandService {
    private final GroupRepository groupRepository;
    private final IUserRepository userRepository;
    @Override
    public Long handle(CreateGroupCommand command) {
        var user=this.userRepository.findById(command.leaderId());
        if(user.isEmpty()) return 0L;
        if(user.get().getLeadingTeam()!=null) return 2L;
        var newGroup=new Team(command.name(), user.get());
        user.get().makeLeader(this.groupRepository.save(newGroup));
        this.userRepository.save(user.get());
        return 1L;
    }

    @Override
    public Long handle(AddMemberToGroupCommand command) {
        var member=this.userRepository.findById(command.memberId());
        var group=this.groupRepository.findById(command.groupId());
        if(member.isEmpty() || group.isEmpty()) return 0L;
        if(member.get().getTeam()!=null) return 2L;
        group.get().addMember(member.get());
        member.get().addToGroup(group.get());
        this.userRepository.save(member.get());
        this.groupRepository.save(group.get());
        return 1L;
    }

    @Override
    public Long handle(ChangeLeaderGroupCommand command) {
        var newLeader=this.userRepository.findById(command.leaderId());
        var group=this.groupRepository.findById(command.groupId());
        if(newLeader.isEmpty() || group.isEmpty()) return 0L;
        group.get().getLeader().setLeadingTeam(null);
        group.get().setLeader(newLeader.get());
        newLeader.get().setLeadingTeam(group.get());
        return 1L;
    }

    @Override
    public Long handle(CreateGroupWithoutLeaderCommand command) {
        var team=new Team(command.teamName());
        var group=this.groupRepository.save(team);
        return 1L;
    }
}
