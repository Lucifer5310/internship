package com.example.internship.service;

import com.example.internship.configurationProperties.TokenProperties;
import com.example.internship.dao.entity.Users;
import com.example.internship.dao.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    private final UserRepository userRepository;
    private final TokenProperties tokenProperties;
    private final long accessTokenValidity;
    private final long refreshTokenValidity;

    public JwtService(UserRepository userRepository, TokenProperties tokenProperties,
                      @Value("${jwt.access.expiration}") long accessTokenValidity,
                      @Value("${jwt.refresh.expiration}") long refreshTokenValidity) {

        this.userRepository = userRepository;
        this.tokenProperties = tokenProperties;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String extractUsername (String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public String generateToken (String username, boolean isRefreshToken){
        Optional<Users> optionalUsers = userRepository.findByUsername(username);
        Users users = optionalUsers.get();
        Map<String, Object> claims = new HashMap<>();

        if (users instanceof Users customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getEmail());
            claims.put("role", customUserDetails.getRole());
        }
        return generateToken(claims, username, isRefreshToken);
    }

    public boolean isTokenValid (String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken (Map<String, Object> extraClaims, String username, boolean isRefreshToken) {
        long validity = isRefreshToken ? refreshTokenValidity : accessTokenValidity;
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private Claims extractAllClaims (String token){
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseSignedClaims(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenProperties.getJwt());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
