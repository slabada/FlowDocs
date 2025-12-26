package com.flowdocs.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String jwtSecret) {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Метод для генерации JWT
     *
     * @param id Индентификатор пользователя
     * @return JWT токен
     */
    public String generateToken(Long id) {
        long expirationMs = 3600000L; // 1 час
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    /**
     * Получить индентификатор из токена JWT
     *
     * @param token JWT токен
     * @return Индентификатор пользователя
     */
    public Long getUserId(String token) {
        var userId = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.parseLong(userId);
    }

    /**
     * Валидация токена
     *
     * @param token JWT токен
     * @return {@link Boolean}
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
