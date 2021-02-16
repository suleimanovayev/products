package com.internet.shop.products.controller;

import com.internet.shop.products.dto.UserDto;
import com.internet.shop.products.entity.Role;
import com.internet.shop.products.entity.User;
import com.internet.shop.products.exception.EmailExistException;
import com.internet.shop.products.mapper.UserMapper;
import com.internet.shop.products.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.Collections;

@Component
@RequestScope
@RequiredArgsConstructor
@Getter
@Setter
public class RegistrationController implements Serializable {
    private final UserService userService;
    private UserMapper userMapper;
    private UserDto userDto;

    @PostConstruct
    public void init() {
        userDto = new UserDto();
    }

    public String save() throws EmailExistException {
        if (!userService.isEmailAlreadyExist(userDto.getEmail())) {
            User user = userMapper.map(userDto);
            user.setRoles(Collections.singleton(new Role("USER")));
            userService.save(user);
            return "/products.jsf";
        }
        throw new EmailExistException("Email is already taken, please choose another");
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object password) throws ValidatorException {
        if (!password.equals(userDto.getPasswordConfirm())) {
            FacesMessage message = new FacesMessage("Password confirm is not equal to password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
