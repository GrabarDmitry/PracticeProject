package com.auto.practiceproject.security.jwt;

import com.auto.practiceproject.exception.JwtAuthenticationException;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final UserDetailsServiceImpl detailsService;

    @Value("${jwt.token.expired}")
    private Long tokenExpired;

    @Value("${jwt.token.secret}")
    private String secret;

    public String createToken(UserDetailsImpl user) {
        log.trace("Token Provider method called to create Token for User with id: {}", user.getUser().getId());
        Claims claims = Jwts.claims().setSubject(user.getUser().getEmail());
        claims.put("email", user.getUser().getEmail());

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenExpired);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, getEncodeSecret())
                .compact();
    }

    protected Authentication getAuthentication(String token) {
        log.trace("Token Provider method called to get Authentication, token: {}", token);
        UserDetails userDetails = this.detailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    protected String getUsername(String token) {
        log.trace("Token Provider method called to get UserName, token: {}", token);
        return Jwts.parser().setSigningKey(getEncodeSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    protected boolean validateToken(String token) {
        log.trace("Token Provider method called to validate Token, token: {}", token);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(getEncodeSecret()).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                log.error("Token is invalid, token: {}", token);
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT token is expired or invalid, token: {}", token);
            throw new JwtAuthenticationException("JWT token is expired or invalid!");
        }
    }

    protected String resolveToken(HttpServletRequest req) {
        log.trace("Token Provider method called to resolve Token");
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        log.warn("Authorization param is null or isn't start with 'Bearer ', Authorization: {}", bearerToken);
        return null;
    }

    private String getEncodeSecret() {
        log.trace("Token Provider method called to get Encode Secret");
        return Base64.getEncoder().encodeToString(secret.getBytes());
    }

}
