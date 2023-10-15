package com.oauth.oauthserver.serverconfig;

import com.oauth.oauthserver.services.JPAUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JPAUserDetailsService jpaUserDetailsService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        assert jpaUserDetailsService != null;
        UserDetails user = jpaUserDetailsService.loadUserByUsername(username);

        boolean authenticated = passwordEncoder.matches(password, user.getPassword());

        if(authenticated){
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }

        return null;
    }

    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) { //Type of authentication
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
