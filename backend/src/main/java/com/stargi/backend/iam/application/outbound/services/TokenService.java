package com.stargi.backend.iam.application.outbound.services;

import java.util.List;

public interface TokenService {
    String generateToken(String username);
    String generateTokenWithId(String username,Long id);
    String generateTokenWithIdAndRoles(String username, Long id, List<String> roles);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
    List<String> getRolesFromToken(String token);

}
