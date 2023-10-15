package com.oauth.oauthserver.services;

import com.oauth.oauthserver.modal.Client;
import com.oauth.oauthserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientRepositoryServiceImpl implements ClientRepositoryService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * @return
     */
    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Client> getClentById(String id) {
        return clientRepository.findById(id);
    }

    /**
     * @param registeredClient
     */
//    @Override
//    public void save(RegisteredClient registeredClient) {
////        clientRepository.save(registeredClient);
//    }
//
//    /**
//     * @param id
//     * @return
//     */
//    @Override
//    public RegisteredClient findById(String id) {
//        return null;
//    }
//
//    /**
//     * @param clientId
//     * @return
//     */
//    @Override
//    public RegisteredClient findByClientId(String clientId) {
//        return null;
//    }
}
