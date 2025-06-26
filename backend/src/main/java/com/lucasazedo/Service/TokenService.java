package com.lucasazedo.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lucasazedo.Model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
public class TokenService {

    private static final String SECRET = "my-secret";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public String generateToken(User user){

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(ALGORITHM);
    }

    public String validateToken(String token){
        try{
            DecodedJWT decodedJWT = JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return decodedJWT.getSubject();
        }
        catch(JWTVerificationException e){
            throw new JWTVerificationException("Token inválido");
        }
    }
}
