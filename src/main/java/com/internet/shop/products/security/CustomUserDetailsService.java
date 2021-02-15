package com.internet.shop.products.security;

import com.internet.shop.products.entity.Role;
import com.internet.shop.products.entity.User;
import com.internet.shop.products.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Login not found: %s", email)));

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new CustomUserDetails(
                getAuthorities(user.getRoles()),
                user.getPassword(),
                user.getEmail(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked);
    }

    private Set<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().map(Role::getRoleName).map(SimpleGrantedAuthority::new).collect(toSet());
    }
}
