package com.stargi.backend.management.application.internal;

import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.management.domain.entities.Team;
import com.stargi.backend.management.domain.queries.GetGroupByIdQuery;
import com.stargi.backend.management.domain.queries.GetGroupByLeaderIdQuery;
import com.stargi.backend.management.domain.queries.GetGroupByMemberIdQuery;
import com.stargi.backend.management.domain.service.IGroupQueryService;
import com.stargi.backend.management.infrastructure.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupQueryService implements IGroupQueryService {
    private final GroupRepository groupRepository;
    private final IUserRepository userRepository;
    @Override
    public Team handle(GetGroupByIdQuery query) {
        var group=this.groupRepository.findById(query.groupId());
        return group.orElse(null);
    }

    @Override
    public Team handle(GetGroupByMemberIdQuery query) {
        return null;
    }

    @Override
    public Team handle(GetGroupByLeaderIdQuery query) {
        return null;
    }

    @Override
    public List<Team> handle() {
        return this.groupRepository.findAll();
    }
}
