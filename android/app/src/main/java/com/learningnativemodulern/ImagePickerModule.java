package com.learningnativemodulern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

//
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ImagePickerModule extends ReactContextBaseJavaModule {
    private static final int IMAGE_PICKER_REQUEST = 1;
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String E_PICKER_CANCELLED = "E_PICKER_CANCELLED";
    private static final String E_FAILED_TO_SHOW_PICKER = "E_FAILED_TO_SHOW_PICKER";
    private static final String E_NO_IMAGE_DATA_FOUND = "E_NO_IMAGE_DATA_FOUND";

    private Promise mPickerPromise;

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener(){
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent){
            Log.d("onActivityResult_rq", String.valueOf(requestCode) );
            if (requestCode == IMAGE_PICKER_REQUEST){
                if (mPickerPromise != null){
                    Log.d("onActivityResult_rs", String.valueOf(resultCode) );
                    if (resultCode == Activity.RESULT_CANCELED){
                        mPickerPromise.reject(E_PICKER_CANCELLED, "Image picker was cancelled");
                    }else if(resultCode == Activity.RESULT_OK){
                        Uri uri = intent.getData();
                        Log.d("onActivityResult_uri", uri.toString());
                        if (uri == null){
                            mPickerPromise.reject(E_NO_IMAGE_DATA_FOUND, "No image data found");
                        }else{
                            mPickerPromise.resolve(uri.toString());
                        }
                    }
                    mPickerPromise = null;
                }
            }
        }
    };
    ImagePickerModule(ReactApplicationContext reactContext){
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
    }

    @NonNull
    @Override
    public String getName() {
        return "ImagePickerModule";
    }

    @ReactMethod
    public void pickImage(final Promise promise){
        Log.d("pickImage_chekc", "in method Picker");
        Activity currentActivity = getCurrentActivity();
        Log.d("pickImage_currentAct", String.valueOf(currentActivity));

        if (currentActivity == null){
            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }

        mPickerPromise = promise;

        try {
            final Intent galleryIntent = new Intent(Intent.ACTION_PICK);

            galleryIntent.setType("image/*");

            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Pick an Image");

            currentActivity.startActivityForResult(galleryIntent, IMAGE_PICKER_REQUEST);
        }catch (Exception e){
            mPickerPromise.reject(E_FAILED_TO_SHOW_PICKER, e);
            mPickerPromise = null;
        }
    }

}
