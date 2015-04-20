package com.wellshang.eagleeye.receivers;

import android.util.*;

public class FireEyeAction
{
    public static int BATTERY_PLUGGED_AC = 0;
    public static int BATTERY_PLUGGED_USB = 0;
    private static final String TAG = "FireEyeAction";
    private static FireEyeActionListener mListener;
    
    static {
        FireEyeAction.mListener = null;
        FireEyeAction.BATTERY_PLUGGED_AC = 1;
        FireEyeAction.BATTERY_PLUGGED_USB = 2;
    }
    
    public static void ActionBatteryPlugged(final int n) {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onBatteryPlugged(n);
        Log.d("FireEyeAction", "ActionBatteryPlugged");
    }
    
    public static void ActionBatteryUnPlugged() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onBatteryUnPlugged();
        Log.d("FireEyeAction", "ActionBatteryUnPlugged");
    }
    
    public static void ActionExtsdMounted() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onExtsdMounted();
        Log.d("FireEyeAction", "ActionExtsdMounted");
    }
    
    public static void ActionExtsdUnMounted() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onExtsdUnMounted();
        Log.d("FireEyeAction", "ActionExtsdUnMounted");
    }
    
    public static void ActionMediaScannerFinished() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onMediaScannerFinished();
        Log.d("FireEyeAction", "ActionMediaScannerFinished");
    }
    
    public static void ActionMediaScannerStarted() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onMediaScannerStarted();
        Log.d("FireEyeAction", "ActionMediaScannerStarted");
    }
    
    public static void ActionPowerConnected() {
        Log.d("FireEyeAction", "ActionPowerConnected");
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onPowerConnected();
        Log.d("FireEyeAction", "ActionPowerConnected");
    }
    
    public static void ActionPowerDisconnected() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.onPowerDisconnected();
        Log.d("FireEyeAction", "ActionPowerDisconnected");
    }
    
    public static void SetActionListener(final FireEyeActionListener mListener) {
        FireEyeAction.mListener = mListener;
        Log.d("FireEyeAction", "SetActionListener, mListener = " + FireEyeAction.mListener);
    }
    
    public static boolean getRecordingStatus() {
        return FireEyeAction.mListener != null && FireEyeAction.mListener.getRecordingStatus();
    }
    
    public static void uncaughtExceptionStopRecording() {
        if (FireEyeAction.mListener == null) {
            return;
        }
        FireEyeAction.mListener.uncaughtExceptionStopRecording();
    }
    
    public interface FireEyeActionListener
    {
        boolean getRecordingStatus();
        
        void onBatteryPlugged(int p0);
        
        void onBatteryUnPlugged();
        
        void onExtsdMounted();
        
        void onExtsdUnMounted();
        
        void onMediaScannerFinished();
        
        void onMediaScannerStarted();
        
        void onPowerConnected();
        
        void onPowerDisconnected();
        
        void uncaughtExceptionStopRecording();
    }
}
