package com.naviroq.patterns.creational.builder.proComputerShop.demo;

import com.naviroq.patterns.creational.builder.proComputerShop.Computer;
import com.naviroq.patterns.creational.builder.proComputerShop.director.ComputerDirector;

public class Main {
    public static void main(String[] args) {

        ComputerDirector director = new ComputerDirector();

        System.out.println("=== GAMING PC ===");
        Computer gaming = director.buildGamingPC("Intel i9-13900K", "64GB DDR5");
        System.out.println(gaming);

        System.out.println("\n=== OFFICE PC ===");
        Computer office = director.buildOfficePC("Intel i5-13400", "16GB DDR4");
        System.out.println(office);

        System.out.println("\n=== WORKSTATION ===");
        Computer workstation = director.buildWorkstation("AMD Threadripper 7970X", "128GB ECC");
        System.out.println(workstation);

        System.out.println("\n=== BUDGET PC ===");
        Computer budget = director.buildBudgetPC("Intel i3-12100", "8GB DDR4");
        System.out.println(budget);

        System.out.println("\n=== CUSTOM BUILD (no director) ===");
        Computer custom = new Computer.Builder()
                .cpu("AMD Ryzen 7 7800X3D")
                .ram("32GB DDR5")
                .storage("1TB NVMe")
                .gpu("AMD RX 7900 XTX")
                .cooling("Custom Loop")
                .psu("850W Gold")
                .wifi(true)
                .caseType("Open Air")
                .fanCount(0)
                .os("Fedora 39")
                .build();
        System.out.println(custom);
    }
}