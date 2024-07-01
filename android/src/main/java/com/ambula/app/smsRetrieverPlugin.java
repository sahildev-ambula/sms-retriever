package com.ambula.app;

import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsRetriever;
import android.util.Log;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "smsRetriever")
public class smsRetrieverPlugin extends Plugin {

    private static final int SMS_CONSENT_REQUEST = 2;  // Set to an unused request code

    @PluginMethod
    public void requestConsent(PluginCall call) {
        Activity activity = getActivity();
        Intent intent = new Intent(SmsRetriever.ACTION_CONSENT_REQUEST);
        intent.putExtra(SmsRetriever.EXTRA_PHONE_NUMBER, (String) null);
        activity.startActivityForResult(intent, SMS_CONSENT_REQUEST);
        call.resolve();
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        if (requestCode == SMS_CONSENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                Log.d("smsRetrieverPlugin", "Received SMS: " + message);
                // Send the received message back to the UI
                JSObject ret = new JSObject();
                ret.put("message", message);
                notifyListeners("onSMSReceived", ret);
            } else {
                // Consent denied, handle accordingly
            }
        }
    }

    private smsRetriever implementation = new smsRetriever();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
}
