package com.tiendadeportiva.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        
        
        String token = getTokenFromRequest(request);

        
        if (token != null && jwtTokenProvider.validateToken(token)) {
            
            String email = jwtTokenProvider.getEmailFromToken(token);
            
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, 
                null, 
                Collections.emptyList() // Sin roles por defecto
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        
        filterChain.doFilter(request, response);
    }

   
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); 
        }
        return null;
    }
}