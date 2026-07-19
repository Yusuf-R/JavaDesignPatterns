package com.naviroq.patterns.structural.composite.organisation.demo;

import com.naviroq.patterns.structural.composite.organisation.staff.StaffBluePrint;
import com.naviroq.patterns.structural.composite.organisation.staff.department.Department;
import com.naviroq.patterns.structural.composite.organisation.staff.individual.Individual;

public class Main {
    public static void main(String[] args) {
        // 1. Create individual leaf workers
        StaffBluePrint abdulwasiu = new Individual("Abdulwasiu", "Developer", 80000);
        StaffBluePrint fatimah = new Individual("Fatimah", "UI/UX", 95000);
        StaffBluePrint bob = new Individual("Bob", "Developer", 85000);
        StaffBluePrint charlie = new Individual("Charlie", "Designer", 75000);
        StaffBluePrint diana = new Individual("Diana", "QA Engineer", 70000);

        Department engineering = new Department("Engineering");
        engineering.add(abdulwasiu);
        engineering.add(fatimah);

        // Design team
        Department design = new Department("Design");
        design.add(charlie);
        design.add(bob);

        // QA team
        Department qa = new Department("Quality Assurance");
        qa.add(diana);

        // Product division — contains departments AND individual employees
        Department product = new Department("Product Division");
        product.add(engineering);  // A department!
        product.add(design);       // A department!
        product.add(qa);           // A department!

        // CEO's direct report — a manager who is also an individual
        StaffBluePrint mariam = new Individual("Mariam", "CTO", 150000);
        product.add(mariam);  // Individual added directly to composite

        // Show everything
        System.out.println("=== COMPANY STRUCTURE ===");
        product.showDetails();

        // Get total salary — one call, handles entire tree
        System.out.println("\n=== TOTAL PAYROLL ===");
        System.out.println("Total salary: $" + product.getSalary());

        // Get sub-department salary
        System.out.println("\n=== ENGINEERING BUDGET ===");
        System.out.println("Engineering total: $" + engineering.getSalary());
    }
}