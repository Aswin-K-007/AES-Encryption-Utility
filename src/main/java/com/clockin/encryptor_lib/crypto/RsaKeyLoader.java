package com.clockin.encryptor_lib.crypto;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaKeyLoader {

    /**
     * Load Public Key from X509 Certificate (.crt)
     */
    public static PublicKey loadPublicKeyFromCertificate(String path) throws Exception {
    	System.out.println("RsaKeyLoader:loadPublicKeyFromCertificate");

        InputStream is = RsaKeyLoader.class
                .getClassLoader()
                .getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("Certificate not found: " + path);
        }

        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        X509Certificate certificate =
                (X509Certificate) factory.generateCertificate(is);

        return certificate.getPublicKey();
    }

    /**
     * Load Public Key from PEM (-----BEGIN PUBLIC KEY-----)
     */
    public static PublicKey loadPublicKey(String path) throws Exception {

        String key = readPemFile(path)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /**
     * Load Private Key from PEM (-----BEGIN PRIVATE KEY-----)
     */
    public static PrivateKey loadPrivateKey(String path) throws Exception {

        String key = readPemFile(path)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    private static String readPemFile(String path) throws Exception {
        InputStream is = RsaKeyLoader.class
                .getClassLoader()
                .getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("Key file not found: " + path);
        }

        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }
}
