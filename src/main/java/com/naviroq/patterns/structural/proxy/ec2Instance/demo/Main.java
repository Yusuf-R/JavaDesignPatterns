package com.naviroq.patterns.structural.proxy.ec2Instance.demo;

import com.naviroq.patterns.structural.proxy.ec2Instance.EC2Services;
import com.naviroq.patterns.structural.proxy.ec2Instance.ec2proxy.EC2ProxyInstance;

public class Main {
    public static void main(String[] args) {
        EC2Services service = new EC2ProxyInstance();

        System.out.println("=== FIRST CALL ===");
        String r1 = service.compute("AtlantaServer-AS2");
        System.out.println(r1);

        System.out.println("\n=== SECOND CALL (same input) ===");
        String r2 = service.compute("AtlantaServer-AS2");
        System.out.println(r2);

        System.out.println("\n=== THIRD CALL (new input) ===");
        String r3 = service.compute("SouthAtlantic-SE2");
        System.out.println(r3);

        System.out.println("\n=== FOURTH CALL (cached input) ===");
        String r4 = service.compute("SouthAtlantic-SE2");
        System.out.println(r4);
    }
}
