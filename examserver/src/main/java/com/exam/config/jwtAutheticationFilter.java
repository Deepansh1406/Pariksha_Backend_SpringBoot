package com.exam.config;


import com.exam.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class jwtAutheticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private jwtUtils jwtutils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final  String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String username= null;
        String jwtToken= null;
        if(requestTokenHeader != null  && requestTokenHeader.startsWith("Bearer")){
//            Yes
            jwtToken= requestTokenHeader.substring(7);
            try {
                username =this.jwtutils.extractUsername(jwtToken);
            }
            catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("Jwt Token is expired");
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
            }

        }else{
//            NO
            System.out.println("Invalid Token ,Not Start with  Bearer String");
        }


//        Validated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtutils.validateToken(jwtToken, userDetails)) {
                // Token is Valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }


        } else {
            System.out.println("Token is not valid");
        }

        filterChain.doFilter(request, response);
    }
}