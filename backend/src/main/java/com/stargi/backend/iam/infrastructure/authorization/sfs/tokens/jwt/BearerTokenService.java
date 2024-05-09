package com.stargi.backend.iam.infrastructure.authorization.sfs.tokens.jwt;

import com.stargi.backend.iam.application.outbound.services.TokenService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest Request);
    String generateToken(Authentication authentication);
}
