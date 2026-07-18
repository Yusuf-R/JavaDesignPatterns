package com.naviroq.patterns.creational.abstractfactory.cloud.orchestrator;


import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.CloudProviderFactory;

public class CloudApplication {

    private final Compute compute;
    private final Database database;
    private final Storage storage;
    private final String providerName;

    public CloudApplication(CloudProviderFactory factory, String providerName) {
        // The factory creates the ENTIRE family of products.
        this.compute = factory.createCompute();
        this.database = factory.createDatabase();
        this.storage = factory.createStorage();
        this.providerName = providerName;
    }

    // The "deploy" method orchestrates the whole provisioning flow.
    public void deployInfrastructure() {
        System.out.println("=====================================================================");
        System.out.println("  🚀 DEPLOYING INFRASTRUCTURE ON " + providerName.toUpperCase());
        System.out.println("=====================================================================");

        // 1. Provision Compute
        System.out.println("\n--- [1] Provisioning Compute ---");
        compute.startInstance("i-" + System.currentTimeMillis());
        System.out.println("    Instance Type: " + compute.getComputeType());

        // 2. Provision Database
        System.out.println("\n--- [2] Provisioning Database ---");
        database.connect();
        System.out.println("    Database Type: " + database.getDatabaseType());

        // 3. Provision Storage
        System.out.println("\n--- [3] Provisioning Storage ---");
        byte[] dummyData = "Hello Cloud!".getBytes();
        storage.upload("welcome.txt", dummyData);
        System.out.println("    Storage Type: " + storage.getStorageType());

        // 4. Summary
        printSummary();

        // 5. Cleanup (optional)
        // teardownInfrastructure();
    }

    private void printSummary() {
        System.out.println("\n=====================================================================");
        System.out.println("  ✅ DEPLOYMENT SUMMARY (" + providerName.toUpperCase() + ")");
        System.out.println("=====================================================================");
        System.out.println("  🖥️  Compute  : " + compute.getComputeType());
        System.out.println("  🗄️  Database : " + database.getDatabaseType());
        System.out.println("  💾 Storage  : " + storage.getStorageType());
        System.out.println("=====================================================================");
        System.out.println("  🌐 Infrastructure is ready to serve traffic!");
        System.out.println("=====================================================================\n");
    }

   // Optional: Cleanup method to stop/destroy resources
    public void teardownInfrastructure() {
        System.out.println("--- Tearing down " + providerName + " infrastructure ---");
        compute.stopInstance("i-12345");
        database.disconnect();
        System.out.println("    Cleanup complete.");
    }

    public String getDeploymentConfirmation() {
        return "✅ Successfully deployed " + providerName.toUpperCase() + " infrastructure. All services are ready!";
    }
}