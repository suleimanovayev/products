package com.internet.shop.products.controller;

import com.internet.shop.products.dto.UserDto;
import com.internet.shop.products.exception.EmailExistException;
import com.internet.shop.products.mapper.UserMapper;
import com.internet.shop.products.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component("registrationController")
@Scope(value = "session")
@RequiredArgsConstructor
public class RegistrationController implements Serializable {
    private final UserService userService;
    private UserMapper userMapper;
    private UserDto userDto;
    private String passwordConfirm;

    @PostConstruct
    public void init() {
        userDto = new UserDto();
    }

    public String save() throws EmailExistException {
        if(!userService.isEmailAlreadyExist(userDto.getEmail())) {
            userService.save(userMapper.map(userDto));
            return "/products.jsf";
        }
        throw new EmailExistException("Email is already taken, please choose another");
    }

    public UserService getUserService() {
        return userService;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
