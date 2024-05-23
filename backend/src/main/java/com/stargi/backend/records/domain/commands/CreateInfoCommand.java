package com.stargi.backend.records.domain.commands;

public record CreateInfoCommand(String ruc, String businessName, String country, Long userId, String stage,
                                String commentary, String oppNumber, String product, Long units, Long realRent,
                                String contact, String contactNumber, String email, String expirationAt,
                                String updatedAt, String closeAt, Long salesManagerId, Long executiveId) {
}

