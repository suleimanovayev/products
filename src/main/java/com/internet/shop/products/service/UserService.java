package com.internet.shop.products.service;

import com.internet.shop.products.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService extends CrudService<User> {

    Optional<User> findByEmail(String userEmail);

    boolean isEmailAlreadyExist(String email);
}
