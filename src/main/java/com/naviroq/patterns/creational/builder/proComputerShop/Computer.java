package com.naviroq.patterns.creational.builder.proComputerShop;
// Computer.java

public class Computer {

    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final String cooling;
    private final String psu;
    private final boolean wifi;
    private final boolean bluetooth;
    private final String caseType;
    private final int fanCount;
    private final String os;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.cooling = builder.cooling;
        this.psu = builder.psu;
        this.wifi = builder.wifi;
        this.bluetooth = builder.bluetooth;
        this.caseType = builder.caseType;
        this.fanCount = builder.fanCount;
        this.os = builder.os;
    }

    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getCooling() { return cooling; }
    public String getPsu() { return psu; }
    public boolean hasWifi() { return wifi; }
    public boolean hasBluetooth() { return bluetooth; }
    public String getCaseType() { return caseType; }
    public int getFanCount() { return fanCount; }
    public String getOs() { return os; }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", cooling='" + cooling + '\'' +
                ", psu='" + psu + '\'' +
                ", wifi=" + wifi +
                ", bluetooth=" + bluetooth +
                ", case='" + caseType + '\'' +
                ", fans=" + fanCount +
                ", os='" + os + '\'' +
                '}';
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage = "512GB SSD";
        private String gpu = "Integrated";
        private String cooling = "Air Cooling";
        private String psu = "550W Bronze";
        private boolean wifi = false;
        private boolean bluetooth = false;
        private String caseType = "Mid Tower";
        private int fanCount = 2;
        private String os = "No OS";

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder cooling(String cooling) {
            this.cooling = cooling;
            return this;
        }

        public Builder psu(String psu) {
            this.psu = psu;
            return this;
        }

        public Builder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Builder bluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Builder caseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        public Builder fanCount(int fanCount) {
            this.fanCount = fanCount;
            return this;
        }

        public Builder os(String os) {
            this.os = os;
            return this;
        }

        public Computer build() {
            if (cpu == null || ram == null) {
                throw new IllegalStateException("CPU and RAM are required");
            }
            return new Computer(this);
        }
    }
}
