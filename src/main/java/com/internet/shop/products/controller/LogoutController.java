package com.internet.shop.products.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestScope
@Component
public class LogoutController {

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.logout();
            log.info("Successful logout");
        } catch (ServletException e) {
            log.error("Logout failed");
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "/login.jsf";
    }
}
