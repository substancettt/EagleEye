package com.wellshang.eagleeye.main;

import java.text.*;
import android.content.*;
import android.os.*;
import java.io.*;
import java.lang.Thread.UncaughtExceptionHandler;
import android.util.*;
import java.util.*;
import com.wellshang.eagleeye.*;
import com.wellshang.eagleeye.receivers.*;

public class CrashHandler implements UncaughtExceptionHandler
{
    public static final String TAG = "CrashHandler";
    private static CrashHandler instance;
    private SimpleDateFormat formatter;
    private Map<String, String> infos;
    private Context mContext;
    private UncaughtExceptionHandler mDefaultHandler;
    
    static {
        CrashHandler.instance = null;
    }
    
    public static CrashHandler getInstance() {
        if (CrashHandler.instance == null) {
            CrashHandler.instance = new CrashHandler();
        }
        return CrashHandler.instance;
    }
    
    private boolean handleException(final Throwable t) {
        if (t == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();
            }
        }.start();
        this.saveCrashInfo2File(t);
        return true;
    }
    
    private String saveCrashInfo2File(Throwable t) {
        final StringBuffer sb = new StringBuffer();
        for (final Map.Entry<String, String> entry : this.infos.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        for (t = t.getCause(); t != null; t = t.getCause()) {
            t.printStackTrace(printWriter);
        }
        printWriter.close();
        sb.append(stringWriter.toString());
        try {
            final String string = "crash-" + this.formatter.format(new Date()) + "-" + System.currentTimeMillis() + ".log";
            if (Environment.getExternalStorageState().equals("mounted")) {
                final File file = new File("/crash/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                final FileOutputStream fileOutputStream = new FileOutputStream("/crash/" + string);
                fileOutputStream.write(sb.toString().getBytes());
                fileOutputStream.close();
            }
            return string;
        }
        catch (Exception ex) {
            Log.e("CrashHandler", "an error occured while writing file...", (Throwable)ex);
            return null;
        }
    }
    
    public void collectDeviceInfo(final Context p0) {
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void init(final Context mContext) {
        this.mContext = mContext;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.infos = new HashMap<String, String>();
        this.formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)this);
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        this.handleException(t);
//        FireEyeShare.getFireEyeConfig(this.mContext).setAppErrorExit(true);
//        FireEyeShare.getFireEyeConfig(this.mContext).setIsRecordingBeforeError(FireEyeAction.getRecordingStatus());
//        FireEyeAction.uncaughtExceptionStopRecording();
        this.mDefaultHandler.uncaughtException(thread, t);
    }
}
