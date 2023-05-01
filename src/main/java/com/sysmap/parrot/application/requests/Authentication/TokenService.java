package com.sysmap.parrot.application.requests.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sysmap.parrot.domain.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String createToken(User user) {
        return JWT.create()
                .withIssuer("Posts")
                .withSubject(user.getUsername())
                .withClaim("name", user.getName())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10)
                .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("SECRET"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("SECRET"))
                .withIssuer("Posts")
                .build().verify(token).getSubject();
    }
}
