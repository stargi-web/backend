package com.stargi.backend.iam.application.outbound.hashing;

public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword,String encodedPassword);

}
