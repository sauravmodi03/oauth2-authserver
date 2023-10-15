package com.oauth.oauthserver.services;

import com.oauth.oauthserver.modal.Client;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface ClientRepositoryService {

    List<Client> getClients();

    Optional<Client> getClentById(String id);
}
