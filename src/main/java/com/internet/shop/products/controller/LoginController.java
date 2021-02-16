package com.internet.shop.products.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;


@RequestScope
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class LoginController implements Serializable {
    private final AuthenticationManager authenticationManager;
    private String email;
    private String password;

    public String login() {
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "/products.jsf";
    }
}
