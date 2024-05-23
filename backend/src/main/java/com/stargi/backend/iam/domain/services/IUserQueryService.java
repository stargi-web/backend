package com.stargi.backend.iam.domain.services;

import java.util.List;

import com.stargi.backend.iam.domain.Responses.GetUserByIdResponse;
import com.stargi.backend.iam.domain.queries.GetUserByIdQuery;

public interface IUserQueryService {
    GetUserByIdResponse handle(GetUserByIdQuery query);
    List<GetUserByIdResponse> handle();
}
