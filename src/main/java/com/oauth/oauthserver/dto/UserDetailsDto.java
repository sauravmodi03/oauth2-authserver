package com.oauth.oauthserver.dto;

import com.oauth.oauthserver.modal.AuthorityEntity;
import com.oauth.oauthserver.modal.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto implements UserDetails, Serializable {

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<AuthorityDto> authorities;

    public UserDetailsDto(UserEntity entity) {
        this.username  = entity.getUsername();
        this.password = entity.getPassword();
        this.accountNonExpired = entity.isAccountNotExpired();
        this.accountNonLocked = entity.isAccountNotLocked();
        this.enabled = entity.isEnabled();
        this.credentialsNonExpired = entity.isCredentialsNonExpired();
        this.authorities = entity.getAuthority().stream().map(a-> new AuthorityDto(a.getAuthority())).collect(Collectors.toList());
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
