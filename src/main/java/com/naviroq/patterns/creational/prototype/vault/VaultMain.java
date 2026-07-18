// VaultMain.java
package com.naviroq.patterns.creational.prototype.vault;

public class VaultMain {
    public static void main(String[] args) {

        // Create original vault
        SecureData originalData = new SecureData(
                "AES-256-KEY-12345",
                "API-TOKEN-SECRET-67890",
                "MASTER-PASSCODE-999"
        );

        Vault masterVault = Vault.create("Alice", originalData, 5);
        System.out.println("=== MASTER VAULT ===");
        System.out.println(masterVault);

        // Clone for backup — deep copy, new vault ID
        Vault backupVault = Vault.deepClone(masterVault);
        System.out.println("\n=== BACKUP VAULT ===");
        System.out.println(backupVault);

        // Clone with elevated access for admin
        Vault adminVault = Vault.deepCloneWithElevatedAccess(masterVault, 10);
        System.out.println("\n=== ADMIN VAULT ===");
        System.out.println(adminVault);

        // Verify secrets are protected
        System.out.println("\n=== SECURITY CHECKS ===");
        System.out.println("Master passcode correct? " + masterVault.verifyPasscode("MASTER-PASSCODE-999"));
        System.out.println("Backup passcode correct? " + backupVault.verifyPasscode("MASTER-PASSCODE-999"));
        System.out.println("Admin passcode correct? " + adminVault.verifyPasscode("MASTER-PASSCODE-999"));

        // Try wrong passcode
        System.out.println("Wrong passcode? " + backupVault.verifyPasscode("WRONG"));

        // Prove vault IDs are unique
        System.out.println("\n=== ID UNIQUENESS ===");
        System.out.println("Master ID: " + masterVault.getVaultId());
        System.out.println("Backup ID: " + backupVault.getVaultId());
        System.out.println("Admin ID: " + adminVault.getVaultId());
        System.out.println("Same ID? " + masterVault.getVaultId().equals(backupVault.getVaultId()));
    }
}