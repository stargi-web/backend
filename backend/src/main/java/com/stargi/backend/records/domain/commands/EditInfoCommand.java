package com.stargi.backend.records.domain.commands;

public record EditInfoCommand(Long infoId,String newStage, String commentary,String expirationAt,String closetAt) {
}
