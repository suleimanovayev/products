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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;

import static java.util.Collections.singleton;

@Slf4j
@Component
@RequestScope
@RequiredArgsConstructor
@Getter
@Setter
public class RegistrationController implements Serializable {
    private final UserService userService;
    private final UserMapper userMapper;

    private UserDto userDto;

    @PostConstruct
    public void init() {
        userDto = new UserDto();
    }

    public String save() throws EmailExistException {
        if (!userService.isEmailAlreadyExist(userDto.getEmail())) {
            User user = userMapper.map(userDto);
            user.setRoles(singleton(new Role("USER")));
            userService.save(user);
            log.info("Successful saved user with email: {}", userDto.getEmail());
            return "/products.jsf";
        }
        log.error("User with email: {}, already exist", userDto.getEmail());
        throw new EmailExistException(String.format("Email %s is already taken", userDto.getEmail()));
    }
}
