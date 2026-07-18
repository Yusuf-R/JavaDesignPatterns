// SecureData.java — the sensitive payload
package com.naviroq.patterns.creational.prototype.vault;

public class SecureData {
    private final String encryptionKey;
    private final String apiToken;
    private final String passcode;

    public SecureData(String encryptionKey, String apiToken, String passcode) {
        this.encryptionKey = encryptionKey;
        this.apiToken = apiToken;
        this.passcode = passcode;
    }

    // Private copy constructor — only Vault can call it
    private SecureData(SecureData other) {
        this.encryptionKey = other.encryptionKey;
        this.apiToken = other.apiToken;
        this.passcode = other.passcode;
    }

    // Package-private factory — only classes in same package can clone
    static SecureData deepClone(SecureData other) {
        return new SecureData(other);
    }

    // NO getters for sensitive fields — never exposed
    public boolean verifyPasscode(String attempt) {
        return this.passcode.equals(attempt);
    }

    public boolean verifyApiToken(String attempt) {
        return this.apiToken.equals(attempt);
    }

    @Override
    public String toString() {
        return "SecureData{encryptionKey='[REDACTED]', apiToken='[REDACTED]', passcode='[REDACTED]'}";
    }
}