package com.oauth.oauthserver.services;

import com.oauth.oauthserver.dto.UserDetailsDto;
import com.oauth.oauthserver.modal.UserEntity;
import com.oauth.oauthserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class JPAUserDetailsService implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        UserEntity u = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
        return new UserDetailsDto(u);
    }

    /**
     * @param user
     */
    @Override
    public void createUser(UserDetails user) {
        UserEntity entity = new UserEntity(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    /**
     * @param user
     */
    @Override
    public void updateUser(UserDetails user) {

    }

    /**
     * @param username
     */
    @Override
    public void deleteUser(String username) {

    }

    /**
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    /**
     * @param username
     * @return
     */
    @Override
    public boolean userExists(String username) {
        return false;
    }
}
