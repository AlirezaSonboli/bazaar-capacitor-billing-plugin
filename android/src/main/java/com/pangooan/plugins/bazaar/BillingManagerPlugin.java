package com.pangooan.plugins.bazaar;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.PluginMethod;
import android.content.Context;

@CapacitorPlugin(name = "CafeBazaarBilling")
public class BillingManagerPlugin extends Plugin {

    private BillingManager billingManager;

    @PluginMethod
    public void initialize(final PluginCall call) {
        String rsaPublicKey = call.getString("rsaPublicKey");
        Context context = getContext();
        billingManager = new BillingManager(context, rsaPublicKey);
        billingManager.connect(new Runnable() {
            @Override
            public void run() {
                JSObject ret = new JSObject();
                ret.put("message", "Connected successfully");
                call.resolve(ret);
            }
        }, new java.util.function.Consumer<Throwable>() {
            @Override
            public void accept(Throwable error) {
                call.reject("Connection failed: " + error.getMessage());
            }
        });
    }

    @PluginMethod
    public void disconnect(PluginCall call) {
        if (billingManager != null) {
            billingManager.disconnect();
        }
        JSObject ret = new JSObject();
        ret.put("message", "Disconnected");
        call.resolve(ret);
    }
}
