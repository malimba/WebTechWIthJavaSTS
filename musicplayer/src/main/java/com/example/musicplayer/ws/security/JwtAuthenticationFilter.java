package com.example.musicplayer.ws.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.musicplayer.ws.shared.utils.jwtUtils;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private jwtUtils JwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Check if Authorization header is present and starts with Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer "
            System.out.println("Authorization header: " + authHeader);
System.out.println("Token is valid: " + JwtUtils.validateToken(token));
System.out.println("Extracted email: " +    JwtUtils.getEmailFromToken(token));

            // Validate token
            if (JwtUtils.validateToken(token)) {
                String userEmail = JwtUtils.getEmailFromToken(token);

                // Set authentication in context
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userEmail, null, Collections.emptyList());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }
}
