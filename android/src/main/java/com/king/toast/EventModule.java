package com.king.toast;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class EventModule {

    private final ReactContext mReactContext;

    public EventModule(ReactContext reactContext) {
        mReactContext = reactContext;
    }

    /**
     * 原生模块向JavaScript 发送事件通知
     *
     * @param eventName   事件名
     * @param writableMap 发送的字段
     */
    public void sendEvent(String eventName, @Nullable WritableMap writableMap) {
        mReactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, writableMap);

    }
}
