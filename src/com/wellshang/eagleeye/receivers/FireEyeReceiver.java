package com.wellshang.eagleeye.receivers;

import android.content.*;
import android.preference.*;
import com.wellshang.eagleeye.main.*;

public class FireEyeReceiver extends BroadcastReceiver
{
    public static final String APP_ERROR_START = "com.softwinner.fireeye.apperrorstop.start";
    public static final String APP_ERROR_STOP = "com.softwinner.fireeye.apperrorstop";
    private static final String FIREEYE_AUTO_START = "fireeye-auto-start";
    private static boolean mBoot;
    private String APP_ERROR_EXIT;
    private String IS_RECORDING_BEFORE_ERROR;
    
    static {
        FireEyeReceiver.mBoot = false;
    }
    
    public FireEyeReceiver() {
        this.APP_ERROR_EXIT = "app-error-exit";
        this.IS_RECORDING_BEFORE_ERROR = "is-recording-before-error";
    }
    
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
            if (intent.getDataString().equalsIgnoreCase("file:///mnt/extsd")) {
                FireEyeAction.ActionExtsdMounted();
            }
        }
        else if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
            if (intent.getDataString().equalsIgnoreCase("file:///mnt/extsd")) {
                FireEyeAction.ActionExtsdUnMounted();
            }
        }
        else if (action.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) {
            if (intent.getDataString().equalsIgnoreCase("file:///mnt/extsd") && FireEyeReceiver.mBoot) {
                FireEyeReceiver.mBoot = false;
                FireEyeAction.ActionMediaScannerFinished();
            }
        }
        else if (action.equals("android.intent.action.MEDIA_SCANNER_STARTED")) {
            if (intent.getDataString().equalsIgnoreCase("file:///mnt/extsd") && FireEyeReceiver.mBoot) {
                FireEyeReceiver.mBoot = false;
                FireEyeAction.ActionMediaScannerStarted();
            }
        }
        else {
            if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                FireEyeReceiver.mBoot = true;
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(this.APP_ERROR_EXIT, false).commit();
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(this.IS_RECORDING_BEFORE_ERROR, false).commit();
                return;
            }
            if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("fireeye-auto-start", true)) {
                    intent = new Intent(context, (Class)FireEye.class);
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                }
                FireEyeAction.ActionPowerConnected();
                return;
            }
            if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                FireEyeAction.ActionPowerDisconnected();
                return;
            }
            if (action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(this.APP_ERROR_EXIT, false).commit();
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(this.IS_RECORDING_BEFORE_ERROR, false).commit();
            }
        }
    }
}
