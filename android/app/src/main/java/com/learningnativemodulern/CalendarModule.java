package com.learningnativemodulern;
import android.se.omapi.Session;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.io.IOException;
import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CalendarModule extends ReactContextBaseJavaModule {
    CalendarModule(ReactApplicationContext context){
        super(context);
    }
    private int count = 0;
    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, double count, Boolean bole, ReadableMap rm, ReadableArray ra, Promise pr){
        Log.d("Calendar_string", name);
        Log.d("Calendar_double", String.valueOf(count) );
        Log.d("Calendar_boolean", String.valueOf(bole) );
        Log.d("Calendar_ReadableMap", String.valueOf(rm) );
        Log.d("Calendar_ReadableArray", String.valueOf(ra) );
//        Log.d("Calendar_callback", String.valueOf(cb) );
        Integer eventId = 123;
//        cb.invoke(null, eventId);
        try {
            if(true) throw new Exception("Tung ra cai loi nay");
            pr.resolve("Response of resolve promist " + 123);
        }catch (Exception e){
            pr.reject("Create event error", e.toString());
        }
    }
    @Override
    public Map<String, Object> getConstants(){
        final Map<String, Object> constants = new HashMap<>();
        constants.put("DEFAULT_EVENT_NAME", "New Event");
        return constants;
    }

    @ReactMethod
    public void addListener(String eventName) {
        // Set up any upstream listeners or background tasks as necessary
    }

    @ReactMethod
    public void removeListeners(Integer count) {
        // Remove upstream listeners, stop unnecessary background tasks
    }
    private void sendEvent(String eventName, String message){
        WritableMap params = Arguments.createMap();
        params.putString("message", message);
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }
//    private void onConnected(){
//        WritableMap params = Arguments.createMap();
//        params.putString("eventProperty", "someValue");
//        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
//        this.sendEvent(reactContext, "EventReminder", params);
//    }
    @ReactMethod
    public void test(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
            public void run() {
                count++;
                System.out.println(count);
                sendEvent("count", String.valueOf(count));
            }
        }, 0, 1000);
    }

}
