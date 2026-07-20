package com.naviroq.patterns.behavioural.command.eCommerce;

public class OrderContext {
    private final String orderId;
    private final String customerEmail;
    private final String item;
    private final int quantity;
    private final double amount;
    private final String shippingAddress;

    // Status flags for rollback decisions
    private boolean inventoryReserved = false;
    private boolean paymentCharged = false;
    private boolean shippingLabelCreated = false;

    public OrderContext(String orderId, String customerEmail, String item,
                        int quantity, double amount, String shippingAddress) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
    }

    // Getters & Setters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public boolean isInventoryReserved() {
        return inventoryReserved;
    }

    public void setInventoryReserved(boolean inventoryReserved) {
        this.inventoryReserved = inventoryReserved;
    }

    public boolean isPaymentCharged() {
        return paymentCharged;
    }

    public void setPaymentCharged(boolean paymentCharged) {
        this.paymentCharged = paymentCharged;
    }

    public boolean isShippingLabelCreated() {
        return shippingLabelCreated;
    }

    public void setShippingLabelCreated(boolean shippingLabelCreated) {
        this.shippingLabelCreated = shippingLabelCreated;
    }
}
