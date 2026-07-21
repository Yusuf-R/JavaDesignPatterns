package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

public interface VendingState {
    void insertCoin();
    void selectProduct(String product);
    void dispense();
    void refund();
    String getStateName();
}
