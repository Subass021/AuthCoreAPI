package com.auth.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.Key;

@Component
public class JwtTokenUtil {
    
	private static final String SECRET = "mySuperSecretKeyForJwtWhichShouldBeAtLeast32Chars";
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long expiration = 1000 * 60 * 60 * 10; // 10 hours 

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🆕 STEP 2: Validate token
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println(" JWT expired");
        } catch (UnsupportedJwtException e) {
            System.out.println(" JWT not supported");
        } catch (MalformedJwtException e) {
            System.out.println(" Malformed JWT");
        } catch (@SuppressWarnings("deprecation") SignatureException e) {
            System.out.println(" Invalid signature");
        } catch (IllegalArgumentException e) {
            System.out.println(" Empty claims");
        }
        return false;
    }

    // 🆕 Internal parser
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}

