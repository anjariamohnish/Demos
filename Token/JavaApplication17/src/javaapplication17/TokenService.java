/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author mohnish
 */
enum TokenType {
    REFRESH,
    AUTH
}

public class TokenService {

    public static Token generateToken(String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretKeyProvider.getInstance().getKey());
            String authToken = JWT.create()
                    .withIssuer("domain")
                    .withSubject("AuthToken")
                    .withExpiresAt(Date.from(LocalDateTime.now(ZoneId.of("UTC")).plusHours(24).toInstant(ZoneOffset.UTC)))
                    .withJWTId(userId)
                    .sign(algorithm);
            return new Token(authToken, generateRefreshToken(userId));
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    public static boolean verifyToken(String token) {
        try {
            DecodedJWT jwt = getVerifier(TokenType.AUTH).verify(token);
            return true;
        } catch (InvalidClaimException e) {
            // expired
            System.out.println(e);
            return false;
        } catch (JWTDecodeException e) {
            //invalid token
            System.out.println(e);
        }
        return false;
    }

    public static Token refreshToken(String refreshToken) {
        try {
            DecodedJWT jwt = getVerifier(TokenType.REFRESH).verify(refreshToken);
            return generateToken(jwt.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static String generateRefreshToken(String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretKeyProvider.getInstance().getKey());
            String refreshToken = JWT.create()
                    .withIssuer("domain")
                    .withSubject("RefreshToken")
                    .withJWTId(userId)
                    .sign(algorithm);
            return refreshToken;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static JWTVerifier getVerifier(TokenType tokenType) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretKeyProvider.getInstance().getKey());
            Verification verification = JWT.require(algorithm); // **Reusable verifier instance**
            switch (tokenType) {
                case AUTH:
                    verification.acceptExpiresAt(0);
                    break;
                case REFRESH:
                    verification.withSubject("RefreshToken");
                    break;
            }
            return verification.build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
