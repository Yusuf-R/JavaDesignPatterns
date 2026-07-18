package com.naviroq.patterns.creational.builder.computershop;

public class Computer {

    // Required
    private final String cpu;
    private final String ram;

    // Optional
    private final String storage;
    private final String gpu;
    private final String cooling;
    private final String psu;
    private final boolean hasWifi;
    private final boolean hasBluetooth;
    private final String caseFans;
    private final String extraStorage;

    // Private constructor — only Builder can call it
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.cooling = builder.cooling;
        this.psu = builder.psu;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
        this.caseFans = builder.caseFans;
        this.extraStorage = builder.extraStorage;
    }

    // GETTERS ONLY — immutable once built
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getCooling() { return cooling; }
    public String getPsu() { return psu; }
    public boolean hasWifi() { return hasWifi; }
    public boolean hasBluetooth() { return hasBluetooth; }
    public String getCaseFans() { return caseFans; }
    public String getExtraStorage() { return extraStorage; }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", cooling='" + cooling + '\'' +
                ", psu='" + psu + '\'' +
                ", hasWifi=" + hasWifi +
                ", hasBluetooth=" + hasBluetooth +
                ", caseFans='" + caseFans + '\'' +
                ", extraStorage='" + extraStorage + '\'' +
                '}';
    }

    // ============================================
    // THE BUILDER — static inner class
    // ============================================
    public static class Builder {

        // Required fields
        private final String cpu;
        private final String ram;

        // Optional fields — initialized to defaults
        private String storage = "512GB SSD";
        private String gpu = "Integrated";
        private String cooling = "Air Cooling";
        private String psu = "550W Bronze";
        private boolean hasWifi = false;
        private boolean hasBluetooth = false;
        private String caseFans = "Standard Fans";
        private String extraStorage = null;

        // Constructor requires only the mandatory fields
        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        // Each optional field has its own method
        // Returns Builder — enables method chaining (fluent API)
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

        public Builder wifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }

        public Builder bluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Builder caseFans(String caseFans) {
            this.caseFans = caseFans;
            return this;
        }

        public Builder extraStorage(String extraStorage) {
            this.extraStorage = extraStorage;
            return this;
        }

        // Final build method — creates the Computer
        public Computer build() {
            return new Computer(this);
        }
    }
}
