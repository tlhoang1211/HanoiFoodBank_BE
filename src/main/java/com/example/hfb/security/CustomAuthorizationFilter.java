package com.example.hfb.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hfb.config.UrlConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(UrlConfig.URL_LOGIN)
                || request.getServletPath().equals(UrlConfig.URL_REFRESH_TOKEN)
                || request.getServletPath().equals(UrlConfig.END_POINT_FOOD + "/search")
                || request.getServletPath().contains(UrlConfig.END_POINT_FOOD) && request.getMethod().equals("GET")
                || request.getServletPath().contains(UrlConfig.END_POINT_PAY)
                || request.getServletPath().equals(UrlConfig.END_POINT + "/users") && request.getMethod().equals("POST")){
            filterChain.doFilter(request, response);
        } else {
            //log.error(request.getServletPath().toString());
            //String test = request.getRequestURL().toString();
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    List<SimpleGrantedAuthority> authorities  = new ArrayList<>();
                    Arrays.asList(roles).forEach(role ->{
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                    log.info("hello");
                } catch (Exception ex) {
                    log.info("Error login");
                    log.info(ex.getStackTrace().toString());
                    Map<String, Object> data = new HashMap<>();
                    data.put("status", HttpStatus.FORBIDDEN.value());
                    data.put("message", ex.getMessage());
                    new ObjectMapper().writeValue(response.getOutputStream(), data);
                }
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("status", HttpStatus.FORBIDDEN.value());
                data.put("message", HttpStatus.FORBIDDEN.toString());
                new ObjectMapper().writeValue(response.getOutputStream(), data);
                filterChain.doFilter(request, response);
            }
        }
    }
}
