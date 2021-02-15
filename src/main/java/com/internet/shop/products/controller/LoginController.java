package com.internet.shop.products.controller;

import com.internet.shop.products.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Scope("request")
@Component
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

    public void testMethod() {
        System.out.println("I'm working!");
    }

    private boolean isNotNull(String password, String email) {
        return isEmpty(password) && isEmpty(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
