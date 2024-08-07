package com.procttor.api.security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class IdEncryptionUtil {

    private final String ALGORITHM = "AES";
    
    @Value("${app.jwt-secret}")
    private String SECRET_KEY; 

    private SecretKey secretKey;

    private Cipher cipher;

    // Removed the code from constructor
    public IdEncryptionUtil() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance(ALGORITHM);
    }

    // Initialize the secretKey after all dependencies are injected
    @PostConstruct
    private void init() {
        this.secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    public String encrypt(Long id) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal((String.valueOf(id)).getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public Long decrypt(String encryptedId) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedId);
        String decryptedString = new String(cipher.doFinal(decodedBytes));
        return Long.valueOf(decryptedString);
    }
}
