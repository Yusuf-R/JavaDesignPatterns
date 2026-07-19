package com.naviroq.patterns.structural.bridge.checkout.gateway.direct.bankdeposit;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class BankDeposit implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Verifying manual bank transfer ledger entry for $" + amount +"\nBankDeposit successful!!\n");
    }
}
