package com.pangooan.plugins.bazaar;

import android.content.Context;
import ir.cafebazaar.poolakey.Payment;
import ir.cafebazaar.poolakey.config.PaymentConfiguration;
import ir.cafebazaar.poolakey.config.SecurityCheck;
import ir.cafebazaar.poolakey.callback.ConnectionCallback;

public class BillingManager {

    private Payment payment;

    public BillingManager(Context context, String rsaPublicKey) {
        SecurityCheck securityCheck;
        if (rsaPublicKey == null || rsaPublicKey.isEmpty()) {
            securityCheck = SecurityCheck.Disable;
        } else {
            securityCheck = SecurityCheck.Enable(rsaPublicKey);
        }
        PaymentConfiguration config = new PaymentConfiguration(securityCheck);
        payment = new Payment(context, config);
    }

    public void connect(final Runnable onSuccess, final java.util.function.Consumer<Throwable> onError) {
        payment.connect(new ConnectionCallback() {
            @Override
            public void connectionSucceed() {
                onSuccess.run();
            }

            @Override
            public void connectionFailed(Throwable error) {
                onError.accept(error);
            }

            @Override
            public void disconnected() {
                onError.accept(new Exception("Disconnected"));
            }
        });
    }

    public void disconnect() {
        payment.disconnect();
    }
}
