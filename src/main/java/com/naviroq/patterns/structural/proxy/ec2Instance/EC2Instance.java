package com.naviroq.patterns.structural.proxy.ec2Instance;

public class EC2Instance implements EC2Services {
    @Override
    public String compute(String input) {
        System.out.println("[RealService] Starting EC2 instance: Computing (this takes 5 seconds)...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) { }
        return "Result for: " + input.toUpperCase();
    }
}
