package com.oauth.oauthserver.keys;

import org.springframework.stereotype.Component;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Component
public class KeyManager {

    public RSAKey rsaKey() {
        try {
            KeyPairGenerator g = KeyPairGenerator.getInstance("RSA");
            g.initialize(2048);
            var keypair = g.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) keypair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keypair.getPrivate();
            return  new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}

