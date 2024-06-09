package com.stargi.backend.iam.application.internal;

import java.util.List;

import com.stargi.backend.iam.domain.Responses.LeaderInfo;
import com.stargi.backend.iam.domain.queries.GetUserByGroupQuery;
import com.stargi.backend.iam.domain.queries.IsUserLeaderQuery;
import com.stargi.backend.management.infrastructure.GroupRepository;
import org.springframework.stereotype.Service;

import com.stargi.backend.iam.application.outbound.hashing.HashingService;
import com.stargi.backend.iam.application.outbound.services.TokenService;
import com.stargi.backend.iam.domain.Responses.GetUserByIdResponse;
import com.stargi.backend.iam.domain.queries.GetUserByIdQuery;
import com.stargi.backend.iam.domain.services.IUserQueryService;
import com.stargi.backend.iam.infrastructure.IRoleRepository;
import com.stargi.backend.iam.infrastructure.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryService implements IUserQueryService{

    private final IUserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public GetUserByIdResponse handle(GetUserByIdQuery query) {
        var user=this.userRepository.findById(query.userId());
        if(user.isEmpty()) throw new RuntimeException("Usuario no encontrado");
        return new GetUserByIdResponse(user.get().getId(),user.get().getUsername(),user.get().getFirstName(),user.get().getLastName());
    }

    @Override
    public List<GetUserByIdResponse> handle() {
        var users=this.userRepository.findAll();
        var response= users.stream().map(user->new GetUserByIdResponse(user.getId(),user.getUsername(),user.getFirstName(),user.getLastName())).toList();
        return response;
    }

    @Override
    public List<GetUserByIdResponse> handle(GetUserByGroupQuery query) {
        var group=this.groupRepository.findById(query.groupId());
        if(group.isEmpty())return null;
        var users=this.userRepository.findByTeam(group.get());
        var response= users.stream().map(user->new GetUserByIdResponse(user.getId(),user.getUsername(),user.getFirstName(),user.getLastName())).toList();
        return response;
    }

    @Override
    public LeaderInfo handle(IsUserLeaderQuery query) {
        Long teamId = userRepository.findTeamIdByLeaderId(query.leaderId());
        boolean isLeader = teamId != null;
        return new LeaderInfo(isLeader, teamId);
    }

}
