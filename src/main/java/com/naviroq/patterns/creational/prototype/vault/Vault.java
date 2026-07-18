// Vault.java
package com.naviroq.patterns.creational.prototype.vault;

public class Vault {
    private final String vaultId;
    private final String owner;
    private final SecureData secureData;
    private final int accessLevel;
    private final boolean isBackup;

    private Vault(String vaultId, String owner, SecureData secureData,
                  int accessLevel, boolean isBackup) {
        this.vaultId = vaultId;
        this.owner = owner;
        this.secureData = secureData;
        this.accessLevel = accessLevel;
        this.isBackup = isBackup;
    }

    public static Vault create(String owner, SecureData data, int accessLevel) {
        return new Vault(generateVaultId(), owner, data, accessLevel, false);
    }

    public static Vault deepClone(Vault other) {
        return new Vault(
                generateVaultId(),
                other.owner,
                SecureData.deepClone(other.secureData),
                other.accessLevel,
                true
        );
    }

    public static Vault deepCloneWithElevatedAccess(Vault other, int newLevel) {
        return new Vault(
                generateVaultId(),
                other.owner,
                SecureData.deepClone(other.secureData),
                newLevel,
                true
        );
    }

    private static String generateVaultId() {
        return "VAULT-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
    }

    public String getVaultId() { return vaultId; }
    public String getOwner() { return owner; }
    public int getAccessLevel() { return accessLevel; }
    public boolean isBackup() { return isBackup; }

    public boolean verifyPasscode(String attempt) {
        return secureData.verifyPasscode(attempt);
    }

    public boolean verifyApiToken(String attempt) {
        return secureData.verifyApiToken(attempt);
    }

    @Override
    public String toString() {
        return "Vault{" +
                "vaultId='" + vaultId + '\'' +
                ", owner='" + owner + '\'' +
                ", accessLevel=" + accessLevel +
                ", isBackup=" + isBackup +
                ", secureData=" + secureData +
                '}';
    }
}