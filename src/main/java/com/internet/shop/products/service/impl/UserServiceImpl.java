package com.internet.shop.products.service.impl;

import com.internet.shop.products.entity.User;
import com.internet.shop.products.repository.UserRepository;
import com.internet.shop.products.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(Long entityId) throws EntityNotFoundException {
        return userRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not fount User with id: %s", entityId)));
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) throws EntityNotFoundException {
        return save(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        userRepository.deleteById(entityId);
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public boolean isEmailAlreadyExist(String email) {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("email", ignoreCase());

        User user = new User();
        user.setEmail(email);
        Example<User> example = Example.of(user, modelMatcher);
        return userRepository.exists(example);
    }
}
