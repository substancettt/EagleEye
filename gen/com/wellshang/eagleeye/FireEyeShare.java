package com.wellshang.eagleeye;

import com.wellshang.eagleeye.media.*;
import android.content.*;
import android.app.*;

public class FireEyeShare
{
    private static FireEyeConfig mConfig;
    private static FireEyeParamsShare mParamsShare;
    private static CameraRecorder mRecorder;
    
    static {
        FireEyeShare.mConfig = null;
        FireEyeShare.mRecorder = null;
        FireEyeShare.mParamsShare = null;
    }
    
    public static CameraRecorder getCameraRecorder() {
        if (FireEyeShare.mRecorder == null) {
            FireEyeShare.mRecorder = new CameraRecorder();
        }
        return FireEyeShare.mRecorder;
    }
    
    public static FireEyeConfig getFireEyeConfig(final Context context) {
        if (FireEyeShare.mConfig == null) {
            FireEyeShare.mConfig = new FireEyeConfig(context);
        }
        return FireEyeShare.mConfig;
    }
    
    public static FireEyeParamsShare getFireEyeParamsShare() {
        return FireEyeShare.mParamsShare;
    }
    
    public static void release() {
        FireEyeShare.mRecorder = null;
    }
    
    public static void setFireEyeParamsShare(final Activity activity) {
        if (FireEyeShare.mParamsShare == null) {
            FireEyeShare.mParamsShare = new FireEyeParamsShare(activity);
        }
    }
}
