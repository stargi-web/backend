package com.stargi.backend.records.interfaces.dtos;

import java.time.LocalDate;

public record InfoDTO(Long id, String ruc, String businessName, String country,String stage,String commentary, String updatedAt,String expirationAt,String closeAt) {
}
