package com.internet.shop.products.filter;

import com.internet.shop.products.entity.Role;

import com.internet.shop.products.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String email = httpServletRequest.getParameter("email");

        Set<Role> roles = userService.findByEmail(email).get().getRoles();


        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getSimpleGrantedAuthority(roles);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                "",
                simpleGrantedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Set<SimpleGrantedAuthority> getSimpleGrantedAuthority(Set<Role> roles) {
        return roles
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleName()))
                .collect(Collectors.toSet());
    }
}
