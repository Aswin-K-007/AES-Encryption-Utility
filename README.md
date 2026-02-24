 AES-Encryption-Utility

 # 🔐 Encryptor Lib (Hybrid AES-GCM + RSA Encryption Library)

A reusable Java encryption library built for secure communication between microservices.

This library implements **Hybrid Encryption** using:

- 🔒 AES-256 (GCM Mode) for payload encryption
- 🔑 RSA (OAEP SHA-256) for AES key encryption

Designed for secure service-to-service communication in distributed systems.

---

## 📦 Maven Coordinates

```xml
<dependency>
    <groupId>com.clockin</groupId>
    <artifactId>encryptor-lib</artifactId>
    <version>1.0.0</version>
</dependency>
