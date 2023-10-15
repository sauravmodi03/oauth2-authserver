package com.oauth.oauthserver.modal;
import jakarta.persistence.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private String clientAuthenticationMethods;
    @Column(length = 1000)
    private String authorizationGrantTypes;
    @Column(length = 1000)
    private String redirectUris;
    @Column(length = 1000)
    private String postLogoutRedirectUris;
    @Column(length = 1000)
    private String scopes;
    @Column(length = 2000)
    private String clientSettings;
    @Column(length = 2000)
    private String tokenSettings;

    public Client(RegisteredClient client) {
        this.clientId = client.getClientId();
        this.clientIdIssuedAt = client.getClientIdIssuedAt();
        this.clientSecret = client.getClientSecret();
        this.clientSecretExpiresAt = client.getClientSecretExpiresAt();
        this.clientName = client.getClientName();
        this.clientAuthenticationMethods = StringUtils.collectionToCommaDelimitedString(client.getClientAuthenticationMethods()
                .stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toList()));
        this.authorizationGrantTypes = StringUtils.collectionToCommaDelimitedString(client.getAuthorizationGrantTypes()
                .stream().map(AuthorizationGrantType::getValue).collect(Collectors.toList()));
        this.redirectUris = StringUtils.collectionToCommaDelimitedString(client.getRedirectUris().stream().toList());
        this.postLogoutRedirectUris = StringUtils.collectionToCommaDelimitedString(client.getPostLogoutRedirectUris().stream().toList());
        this.scopes = StringUtils.collectionToCommaDelimitedString(client.getScopes().stream().toList());
//        this.clientSettings = client.getClientSettings();
//        this.tokenSettings = client.getTokenSettings();
    }

    public Client() {

    }
}
