package com.stargi.backend.records.domain.commands;

public record EditClientCommand(Long clientId,String newStage,String newMessage) {
}
