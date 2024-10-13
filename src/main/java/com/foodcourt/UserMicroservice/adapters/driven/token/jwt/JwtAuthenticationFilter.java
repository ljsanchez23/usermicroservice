package com.foodcourt.UserMicroservice.adapters.driven.token.jwt;

import com.foodcourt.UserMicroservice.adapters.driven.token.util.UserDetailsServiceImpl;
import com.foodcourt.UserMicroservice.configuration.security.util.SecurityConstants;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger log = Logger.getLogger(getClass().getName());

    final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = extractJwtFromRequest(request);
        if (jwt != null && validateToken(jwt)) {
            Claims claims = Jwts.parser()
                    .verifyWith(SecurityConstants.getSignedKey(SecurityConstants.JWT_SECRET_KEY))
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

            String username = claims.getSubject();
            String role = claims.get(SecurityConstants.JWT_AUTHORITY, String.class);

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, Collections.singletonList(authority));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstants.JWT_HEADER);
        if (bearerToken != null && bearerToken.startsWith(SecurityConstants.JWT_BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SecurityConstants.getSignedKey(SecurityConstants.JWT_SECRET_KEY))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            if (claims.getExpiration().before(new Date())) {
                return false;
            }

            return !claims.getExpiration().before(new Date());
        } catch (MalformedJwtException ex) {
            log.info(SecurityConstants.JWT_MALFORMED_TOKEN);
        } catch (ExpiredJwtException ex) {
            log.info(SecurityConstants.JWT_EXPIRED_TOKEN);
        } catch (UnsupportedJwtException ex) {
            log.info(SecurityConstants.JWT_NOT_SUPPORTED);
        } catch (IllegalArgumentException ex) {
            log.info(SecurityConstants.JWT_EMPTY_CLAIM);
        }
        return false;
    }
}
