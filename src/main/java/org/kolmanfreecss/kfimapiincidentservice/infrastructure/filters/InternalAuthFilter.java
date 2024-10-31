package org.kolmanfreecss.kfimapiincidentservice.infrastructure.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * InternalAuthFilter
 * Used to filter the internal authentication.
 * 
 * @version 1.0
 * @author Kolman-Freecss
 */
@Component
public class InternalAuthFilter extends OncePerRequestFilter {
    
    private static final String INTERNAL_AUTH_HEADER = "X-Internal-Auth";
    
    @Value("${gateway.internal-auth-secret}")
    private String expectedInternalAuthSecret;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String internalAuthHeader = request.getHeader(INTERNAL_AUTH_HEADER);
        
        if (internalAuthHeader == null || !internalAuthHeader.equals(expectedInternalAuthSecret)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid internal authentication header.");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/public") || path.equals("/health");
    }
}
