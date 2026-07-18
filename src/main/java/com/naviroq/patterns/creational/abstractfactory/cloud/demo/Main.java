package com.naviroq.patterns.creational.abstractfactory.cloud.demo;

import com.naviroq.patterns.creational.abstractfactory.cloud.factory.CloudProviderFactory;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.CreateFactory;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.ServiceInputs;
import com.naviroq.patterns.creational.abstractfactory.cloud.orchestrator.CloudApplication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // --- 1. DISPLAY BANNER & MENU ---
        System.out.println("=====================================================================");
        System.out.println("          ☁️  CLOUD INFRASTRUCTURE PROVISIONING TOOL ☁️");
        System.out.println("=====================================================================");
        System.out.println("Welcome to the Abstract Factory Cloud Provisioner.");
        System.out.println("We will deploy a full infrastructure stack (Compute + Database + Storage).");
        System.out.println("\n--- Available Cloud Providers ---");
        System.out.println("  1. AWS   (Amazon Web Services)");
        System.out.println("  2. Azure (Microsoft Azure)");
        System.out.println("==============================================");

        // --- 2. GET USER INPUT ---
        String userChoice = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("\n👉 Please enter your preferred cloud provider (aws/azure): ");
            userChoice = scanner.nextLine().trim().toLowerCase();

            if (userChoice.isEmpty()) {
                System.out.println("❌ Input cannot be empty. Please try again.");
                continue;
            }

            // Validate against the Enum
            try {
                // Since our enum has 'aws' and 'azure' (lowercase), we can convert directly.
                ServiceInputs selectedService = ServiceInputs.valueOf(userChoice);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid provider '" + userChoice + "'. Please type 'aws' or 'azure'.");
            }
        }

        // --- 3. CONVERT TO ENUM AND GET FACTORY ---
        ServiceInputs selectedService = ServiceInputs.valueOf(userChoice);
        System.out.println("\n✅ Service Selection Confirmed: " + selectedService.name().toUpperCase());

        CreateFactory creator = new CreateFactory();
        CloudProviderFactory factory = creator.getService(selectedService);

        // --- 4. PROVISION INFRASTRUCTURE ---
        CloudApplication application = new CloudApplication(factory, selectedService.name());
        application.deployInfrastructure();

        // --- 5. DISPLAY FINAL CONFIRMATION ---
        System.out.println("\n" + application.getDeploymentConfirmation());
        System.out.println("🌐 Your cloud environment is ready to accept traffic!");

        scanner.close();

    }
}