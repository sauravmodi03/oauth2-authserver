package com.oauth.oauthserver.modal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class AuthorityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    UserEntity user;

    private String authority;

    public AuthorityEntity(UserEntity user, String authority){
        this.user = user;
        this.authority = authority;
    }

}
