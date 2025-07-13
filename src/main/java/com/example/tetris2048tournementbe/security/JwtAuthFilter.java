package com.example.tetris2048tournementbe.security;

import com.example.tetris2048tournementbe.service.JwtService;
import com.example.tetris2048tournementbe.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String requestPath = request.getServletPath();

        // Güvenlik kontrolünden muaf olan endpoint'ler
        if (requestPath.startsWith("/auth/") && !requestPath.equals("/auth/validate") ||
            requestPath.startsWith("/ws") ||
            requestPath.startsWith("/notifications/")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String username = null;
            String token = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                try {
                    username = jwtService.extractUser(token);
                    logger.debug("JWT işleme alındı, kullanıcı: {}", username);
                } catch (Exception e) {
                    logger.error("JWT işlenirken hata oluştu: {}", e.getMessage());
                }
            } else {
                logger.debug("Authorization header eksik veya 'Bearer ' ile başlamıyor. Endpoint: {}", requestPath);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (jwtService.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        logger.debug("Authentication başarılı, kullanıcı: {}. Endpoint: {}", username, requestPath);
                    } else {
                        logger.debug("Token geçerli değil: {}. Endpoint: {}", token, requestPath);
                    }
                } catch (UsernameNotFoundException e) {
                    logger.error("Kullanıcı bulunamadı: {}", username);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("JWT filter işlenirken beklenmeyen hata: {}", e.getMessage());
            filterChain.doFilter(request, response);
        }
    }
}
