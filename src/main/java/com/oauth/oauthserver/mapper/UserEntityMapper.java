package com.oauth.oauthserver.mapper;

import com.oauth.oauthserver.modal.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@AllArgsConstructor
public class UserEntityMapper {

    private final UserDetails user;
    private final PasswordEncoder passwordEncoder;

    public UserEntity getEntity() {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setEnabled(user.isEnabled());
        entity.setAccountNotExpired(user.isAccountNonExpired());
        entity.setAccountNotLocked(user.isAccountNonLocked());
        entity.setCredentialsNonExpired(user.isCredentialsNonExpired());

        return entity;
    }
}
