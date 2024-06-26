package com.stargi.backend.records.domain.commands;

import com.fasterxml.jackson.databind.JsonNode;

public record CreateClientsFromJsonCommand(Long collectionId, JsonNode clientsJson) {
}
