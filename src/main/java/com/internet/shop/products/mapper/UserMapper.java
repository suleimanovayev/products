package com.internet.shop.products.mapper;

import com.internet.shop.products.dto.UserDto;
import com.internet.shop.products.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDto userDto);
}
