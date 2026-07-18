package com.naviroq.patterns.creational.builder.proComputerShop.director;

import com.naviroq.patterns.creational.builder.proComputerShop.Computer;

public class ComputerDirector {

    public Computer buildGamingPC(String cpu, String ram) {
        return new Computer.Builder()
                .cpu(cpu)
                .ram(ram)
                .storage("2TB NVMe")
                .gpu("NVIDIA RTX 4090")
                .cooling("Liquid Cooling")
                .psu("1000W Platinum")
                .wifi(true)
                .bluetooth(true)
                .caseType("Full Tower RGB")
                .fanCount(6)
                .os("Windows 11 Pro")
                .build();
    }

    public Computer buildOfficePC(String cpu, String ram) {
        return new Computer.Builder()
                .cpu(cpu)
                .ram(ram)
                .storage("512GB SSD")
                .gpu("Integrated")
                .cooling("Air Cooling")
                .psu("450W Bronze")
                .wifi(true)
                .bluetooth(false)
                .caseType("Small Form Factor")
                .fanCount(1)
                .os("Windows 11 Home")
                .build();
    }

    public Computer buildWorkstation(String cpu, String ram) {
        return new Computer.Builder()
                .cpu(cpu)
                .ram(ram)
                .storage("4TB NVMe RAID")
                .gpu("NVIDIA RTX A6000")
                .cooling("Liquid Cooling")
                .psu("1200W Titanium")
                .wifi(false)
                .bluetooth(false)
                .caseType("Full Tower Silent")
                .fanCount(4)
                .os("Ubuntu 22.04 LTS")
                .build();
    }

    public Computer buildBudgetPC(String cpu, String ram) {
        return new Computer.Builder()
                .cpu(cpu)
                .ram(ram)
                .storage("256GB SSD")
                .gpu("Integrated")
                .cooling("Stock Air")
                .psu("400W Bronze")
                .wifi(false)
                .bluetooth(false)
                .caseType("Mini Tower")
                .fanCount(1)
                .os("No OS")
                .build();
    }
}