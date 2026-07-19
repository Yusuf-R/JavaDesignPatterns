package com.naviroq.patterns.structural.bridge.checkout.demo;

import com.naviroq.patterns.structural.bridge.checkout.basic.BasicCheckout;
import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;
import com.naviroq.patterns.structural.bridge.checkout.gateway.card.paypal.PayPalGateway;
import com.naviroq.patterns.structural.bridge.checkout.gateway.card.stripe.StripeGateway;
import com.naviroq.patterns.structural.bridge.checkout.gateway.direct.bankdeposit.BankDeposit;
import com.naviroq.patterns.structural.bridge.checkout.gateway.direct.cash.DirectCashPayment;
import com.naviroq.patterns.structural.bridge.checkout.standard.StandardCheckout;

public class Main {
    public static void main(String[] args) {
        PaymentGateway cashPayment = new DirectCashPayment();
        PaymentGateway bankPayment = new BankDeposit();
        PaymentGateway stripeGateway = new StripeGateway();
        PaymentGateway payPalGateway = new PayPalGateway();

        // user has bought some items and wants to check out using cash
        double amount = 540.63;
        BasicCheckout cashCheckout = new BasicCheckout(cashPayment);
        cashCheckout.completeOrder(amount);

        // another user might want to user bank deposit to pay
        double schoolFeesPayment = 400.11;
        BasicCheckout bankCheckout = new BasicCheckout(bankPayment);
        bankCheckout.completeOrder(schoolFeesPayment);

        // another user might want to user stripe gateway to pay
        double kimiSub = 95.21;
        StandardCheckout stripeCheckout  = new StandardCheckout(stripeGateway);
        stripeCheckout.completeOrder(kimiSub);

        // another user might want to user PayPal gateway to pay
        double gamePad = 1245.80;
        StandardCheckout payPalCheckout  = new StandardCheckout(payPalGateway);
        payPalCheckout.completeOrder(gamePad);
    }
}
