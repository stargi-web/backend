package com.stargi.backend.iam.infrastructure.authorization.sfs.bcrypt;

import com.stargi.backend.iam.application.outbound.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
