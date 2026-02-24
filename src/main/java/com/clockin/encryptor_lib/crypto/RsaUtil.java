package com.clockin.encryptor_lib.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RsaUtil {

    private static final String RSA = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    public static String encryptKey(SecretKey aesKey, PublicKey publicKey) throws Exception {
    	System.out.println("RsaUtil:encryptKey");
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedKey = cipher.doFinal(aesKey.getEncoded());
        return Base64.getEncoder().encodeToString(encryptedKey);
    }

    public static SecretKey decryptKey(String encryptedKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decoded = Base64.getDecoder().decode(encryptedKey);
        byte[] decrypted = cipher.doFinal(decoded);

        return new SecretKeySpec(decrypted, "AES");
    }
}
