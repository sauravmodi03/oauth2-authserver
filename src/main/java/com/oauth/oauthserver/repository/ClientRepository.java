package com.oauth.oauthserver.repository;

import com.oauth.oauthserver.modal.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
