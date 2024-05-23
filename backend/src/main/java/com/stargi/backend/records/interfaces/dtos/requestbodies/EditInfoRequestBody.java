package com.stargi.backend.records.interfaces.dtos.requestbodies;

public record EditInfoRequestBody(String newStage,String newCommentary,String newExpirationAt,String newCloseAt) {
}
