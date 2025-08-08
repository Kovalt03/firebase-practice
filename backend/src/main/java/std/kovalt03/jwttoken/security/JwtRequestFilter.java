package std.kovalt03.jwttoken.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import std.kovalt03.jwttoken.dto.TokenPair;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        
        String accessToken = null;
        String username = null;
        String refreshToken = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            accessToken = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(accessToken);
            } catch (ExpiredJwtException e) {
                refreshToken = request.getHeader("Refresh-Token");
                if(refreshToken != null) {
                    try {
                        username = jwtUtil.extractUsername(refreshToken);
                        if(jwtUtil.validateToken(refreshToken, username)) {
                            Map<String, Object> claims = jwtUtil.extractAllClaims(refreshToken);
                            TokenPair tokenPair = jwtUtil.generateToken(username, claims);
                            String newAccessToken = tokenPair.getAccessToken();
                            String newRefreshToken = tokenPair.getRefreshToken();

                            response.setHeader("Authorization", "Bearer " + newAccessToken);
                            response.setHeader("Refresh-token", newRefreshToken);
                        } else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Refresh token is invalid or expired");
                            return;
                        }
                    }catch (ExpiredJwtException ex) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Refresh token is invalid or expired");
                        return;
                    }
                }
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if(jwtUtil.validateToken(accessToken, username)) {
                UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken (
                        new User(username, "", Collections.singletonList(new SimpleGrantedAuthority("USER"))),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
    