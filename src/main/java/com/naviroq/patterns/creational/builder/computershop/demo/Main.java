package com.naviroq.patterns.creational.builder.computershop.demo;

import com.naviroq.patterns.creational.builder.computershop.Computer;

public class Main {
    public static void main(String[] args) {

        // Gaming rig — set only what matters
        Computer gamingPC = new Computer.Builder("Intel i9-13900K", "64GB DDR5")
                .storage("2TB NVMe")
                .gpu("NVIDIA RTX 4090")
                .cooling("Liquid Cooling")
                .psu("1000W Platinum")
                .wifi(true)
                .bluetooth(true)
                .caseFans("RGB Fans x6")
                .build();

        System.out.println("=== Gaming PC ===");
        System.out.println(gamingPC);

        // Office laptop — minimal config, defaults handle the rest
        Computer officeLaptop = new Computer.Builder("Intel i5-1340P", "16GB DDR4")
                .wifi(true)
                .build();

        System.out.println("\n=== Office Laptop ===");
        System.out.println(officeLaptop);

        // Server — no GPU, no WiFi, max storage
        Computer server = new Computer.Builder("AMD EPYC 9654", "512GB ECC")
                .storage("8TB NVMe RAID")
                .psu("1200W Titanium")
                .extraStorage("24TB HDD Array")
                .build();

        System.out.println("\n=== Server ===");
        System.out.println(server);
    }
}