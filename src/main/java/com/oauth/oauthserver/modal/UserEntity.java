package com.oauth.oauthserver.modal;

import com.oauth.oauthserver.dto.UserDetailsDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "users")
@Data
@NoArgsConstructor
@ToString(exclude = "authority")
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled = true;
    private boolean accountNotExpired = true;
    private boolean accountNotLocked = true;
    private boolean credentialsNonExpired = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthorityEntity> authority = new ArrayList<>();

    public UserEntity(UserDetails dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.enabled = dto.isEnabled();
        this.accountNotExpired = dto.isAccountNonExpired();
        this.accountNotLocked = dto.isAccountNonLocked();
        this.credentialsNonExpired = dto.isCredentialsNonExpired();
        dto.getAuthorities().forEach(a-> this.authority.add(new AuthorityEntity( this, a.getAuthority())));
    }

}
