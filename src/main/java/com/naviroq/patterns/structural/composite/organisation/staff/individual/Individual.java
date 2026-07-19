package com.naviroq.patterns.structural.composite.organisation.staff.individual;

import com.naviroq.patterns.structural.composite.organisation.staff.StaffBluePrint;

public class Individual implements StaffBluePrint {
    private final String name;
    private final String role;
    private final double salary;

    public Individual(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public void showDetails() {
        System.out.println("  " + name + " (" + role + ") - $" + salary);
    }
}
