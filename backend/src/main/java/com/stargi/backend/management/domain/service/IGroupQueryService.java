package com.stargi.backend.management.domain.service;

import com.stargi.backend.management.domain.entities.Team;
import com.stargi.backend.management.domain.queries.GetGroupByIdQuery;
import com.stargi.backend.management.domain.queries.GetGroupByLeaderIdQuery;
import com.stargi.backend.management.domain.queries.GetGroupByMemberIdQuery;

import java.util.List;

public interface IGroupQueryService {
    Team handle(GetGroupByIdQuery query);
    Team handle(GetGroupByMemberIdQuery query);
    Team handle(GetGroupByLeaderIdQuery query);
    List<Team> handle();
}
