package com.example.wolseytechhr;

import android.os.Build;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

public class Login {
    private String userName;
    private String password;
    private String company;

    public Login(){
        this.userName = "";
        this.password = "";
        this.company = "";
    }

    public Login(String userName, String password, String company){
        this.userName = userName;
        this.password = encrypt(password);
        this.company = company;
    }

    private String encrypt(String input) {
        try {
            // GENERATE RSA KEY PAIR
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1000); // You can adjust the key size as needed
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // ENCRYPT THE PASSWORD WITH THE PUBLIC KEY
            byte[] encryptedBytes = encryptWithRSA(input.getBytes(), publicKey);

            // SIGN THE ENCRYPTED PASSWORD WITH THE PRIVATE KEY (OPTIONAL)
            String signature = signWithRSA(encryptedBytes, privateKey);

            // RETURN THE BASE64 ENCODED ENCRYPTED PASSWORD AND SIGNATURE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(encryptedBytes) + "|" + signature;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private byte[] encryptWithRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private String signWithRSA(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signatureBytes = signature.sign();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(signatureBytes);
        }
        return null;
    }
}
