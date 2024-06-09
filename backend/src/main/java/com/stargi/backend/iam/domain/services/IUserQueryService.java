package com.stargi.backend.iam.domain.services;

import java.util.List;

import com.stargi.backend.iam.domain.Responses.GetUserByIdResponse;
import com.stargi.backend.iam.domain.Responses.LeaderInfo;
import com.stargi.backend.iam.domain.queries.IsUserLeaderQuery;
import com.stargi.backend.iam.domain.queries.GetUserByGroupQuery;
import com.stargi.backend.iam.domain.queries.GetUserByIdQuery;

public interface IUserQueryService {
    GetUserByIdResponse handle(GetUserByIdQuery query);
    List<GetUserByIdResponse> handle();
    List<GetUserByIdResponse> handle(GetUserByGroupQuery query);
    LeaderInfo handle(IsUserLeaderQuery query);
}
