package com.king.toast;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;


public class MyToastPluginModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final EventModule mEventModule;
    private final Handler mHandler;
    private int mIndex;

    public MyToastPluginModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        mEventModule = new EventModule(reactContext);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public String getName() {
        return "MyToastPlugin";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("EVENT_NAME_COUNT", EventName.COUNT);
        return map;
    }

    @ReactMethod
    public void testCallBack(String message, Callback callback) {
        Toast.makeText(reactContext, message, Toast.LENGTH_SHORT).show();
        if (callback != null) {
            callback.invoke("CallBack: " + message);
        }
    }

    @ReactMethod
    public void testPromise(String message, Promise promise) {
        Toast.makeText(reactContext, message, Toast.LENGTH_SHORT).show();
        if (promise != null) {
            promise.resolve("Promise");
        }
    }

    @ReactMethod
    public void printActivity(Promise promise) {
        if (promise != null) {
            promise.resolve(getActivity(reactContext).toString());
        }
    }

    @ReactMethod
    public void count() {
        mIndex = 0;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIndex++;
                WritableMap map = Arguments.createMap();
                map.putInt("index", mIndex);
                mEventModule.sendEvent(EventName.COUNT, map);
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    @ReactMethod
    public void stopCount() {
        Toast.makeText(reactContext, "stopCount", Toast.LENGTH_SHORT).show();
        mHandler.removeCallbacksAndMessages(null);
    }


    public Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ReactContext) {
            ReactContext reactContext = ((ReactContext) context);
            return reactContext.getCurrentActivity();
        }
        return null;
    }
}
