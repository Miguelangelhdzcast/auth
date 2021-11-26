package com.proyectoestadias.lototresauth.security.Jwt;

import com.proyectoestadias.lototresauth.security.UserDetailsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImp userDetailsServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
    try{
            String token = getToken(req);
            String email = jwtProvider.getEmailFromToken(token);
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }catch (Exception e){
        logger.error("Error en el metodo doFilter");
    }
    chain.doFilter(req,res);

    }
    private String getToken(HttpServletRequest req){
        String authReq = req.getHeader("Authorization");
        if(authReq != null && authReq.startsWith("Bearer "))
            return authReq.replace("Bearer ", "");
        return null;
    }
}
