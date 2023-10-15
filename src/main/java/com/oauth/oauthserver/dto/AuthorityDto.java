package com.oauth.oauthserver.dto;

import com.oauth.oauthserver.modal.AuthorityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto implements GrantedAuthority, Serializable {

    private String authority;

    public AuthorityDto(AuthorityEntity entity){
        this.authority = entity.getAuthority();
    }

    /**
     * @return
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
