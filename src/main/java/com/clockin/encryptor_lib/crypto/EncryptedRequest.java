package com.clockin.encryptor_lib.crypto;

public class EncryptedRequest {

    private String key;
    private String data;

    public EncryptedRequest(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() { return key; }
    public String getData() { return data; }

    public void setKey(String key) { this.key = key; }
    public void setData(String data) { this.data = data; }
}
