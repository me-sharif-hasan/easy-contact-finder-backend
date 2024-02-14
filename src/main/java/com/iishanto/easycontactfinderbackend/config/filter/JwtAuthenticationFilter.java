package com.iishanto.easycontactfinderbackend.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationErrorDto;
import com.iishanto.easycontactfinderbackend.service.user.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            String userEmail=null;
            try {
                userEmail=jwtService.extractUsername(jwt);
            }catch (Throwable e){
                throw new Exception("JWT token expired!");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("PROCEEDING");
            if (userEmail != null && authentication == null) {
                System.out.println("Authentication available");
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    com.iishanto.easycontactfinderbackend.model.User user= (com.iishanto.easycontactfinderbackend.model.User) authToken.getPrincipal();
                    if(user.getIsPhotoVerified()==null||!user.getIsPhotoVerified()){
                        System.out.println(request.getRequestURL());
                    }
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }else{
//                    throw new IOException("Invalid JWT token");
                }
            }else{
//                throw new IOException("Email and authentication not found");
            }
            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
            exception.printStackTrace();
            filterChain.doFilter(request,response);
        }

    }
}
