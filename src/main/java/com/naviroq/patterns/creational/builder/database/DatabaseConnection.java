// DatabaseConnection.java
package com.naviroq.patterns.creational.builder.database;

public class DatabaseConnection {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final int poolSize;
    private final int timeoutSeconds;
    private final boolean sslEnabled;
    private final boolean readReplica;

    private DatabaseConnection(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.poolSize = builder.poolSize;
        this.timeoutSeconds = builder.timeoutSeconds;
        this.sslEnabled = builder.sslEnabled;
        this.readReplica = builder.readReplica;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public int getPoolSize() { return poolSize; }
    public int getTimeoutSeconds() { return timeoutSeconds; }
    public boolean isSslEnabled() { return sslEnabled; }
    public boolean isReadReplica() { return readReplica; }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", poolSize=" + poolSize +
                ", timeout=" + timeoutSeconds + "s" +
                ", ssl=" + sslEnabled +
                ", readReplica=" + readReplica +
                '}';
    }

    // same constructor like main class
    public static class Builder {
        private String host;
        private int port = 5432;
        private String username;
        private String password;
        private int poolSize = 10;
        private int timeoutSeconds = 30;
        private boolean sslEnabled = true;
        private boolean readReplica = false;

        // provide all the setters method necessary

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder poolSize(int poolSize) {
            this.poolSize = poolSize;
            return this;
        }

        public Builder timeoutSeconds(int timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }

        public Builder sslEnabled(boolean sslEnabled) {
            this.sslEnabled = sslEnabled;
            return this;
        }

        public Builder readReplica(boolean readReplica) {
            this.readReplica = readReplica;
            return this;
        }

        // provide the instantiation method since the main constructor is private
        public DatabaseConnection build() {
            if (host == null || username == null || password == null) {
                throw new IllegalStateException("Host, username, and password are required");
            }
            return new DatabaseConnection(this);
        }
    }
}