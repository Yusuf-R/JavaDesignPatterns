package com.naviroq.patterns.structural.composite.organisation.staff.department;

import com.naviroq.patterns.structural.composite.organisation.staff.StaffBluePrint;

import java.util.ArrayList;
import java.util.List;

public class Department implements StaffBluePrint {
    private final String name;
    private final List<StaffBluePrint> members = new ArrayList<>();  // Holds BOTH types!

    public Department(String name) {
        this.name = name;
    }

    public void add(StaffBluePrint member) {
        members.add(member);
    }

    public void remove(StaffBluePrint member) {
        members.remove(member);
    }

    @Override
    public double getSalary() {
        double total = 0;
        for (StaffBluePrint member : members) {
            total += member.getSalary();  // Same call! Leaf or composite, doesn't matter
        }
        return total;
    }

    @Override
    public void showDetails() {
        System.out.println("[" + name + "]");
        for (StaffBluePrint member : members) {
            member.showDetails();  // Same call! Recursion handles nesting
        }
    }
}
