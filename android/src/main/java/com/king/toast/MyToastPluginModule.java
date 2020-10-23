package com.king.toast;

import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class MyToastPluginModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public MyToastPluginModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "MyToastPlugin";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void sampleMethod2(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("AAAA " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void toast(String message, Promise promise) {
        Toast.makeText(reactContext, message, Toast.LENGTH_SHORT).show();
        if (promise != null) {
            promise.resolve("Promise");
        }
    }
}
