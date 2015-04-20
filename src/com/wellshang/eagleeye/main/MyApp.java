package com.wellshang.eagleeye.main;

import android.view.*;
import android.app.*;
import java.util.*;

public class MyApp extends Application
{
    private static List<Activity> activityList;
    private static MyApp app;
    private WindowManager wm;
    private WindowManager.LayoutParams wmFloatBtnParams;
    private WindowManager.LayoutParams wmMiniParams;
    private WindowManager.LayoutParams wmParams;
    
    public MyApp() {
        this.wm = null;
        this.wmParams = null;
        this.wmMiniParams = null;
        this.wmFloatBtnParams = null;
    }
    
    public static MyApp getInstance() {
        return MyApp.app;
    }
    
    public void addActivity(final Activity activity) {
        MyApp.activityList.add(activity);
    }
    
    public void exit() {
        final Iterator<Activity> iterator = MyApp.activityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }
        ((NotificationManager)this.getSystemService("notification")).cancel(1001);
        System.exit(0);
    }
    
    public WindowManager.LayoutParams getCarReversingMiniLayoutParams() {
        if (this.wmMiniParams == null) {
            this.wmMiniParams = new WindowManager.LayoutParams();
            this.wmMiniParams.type = 2007;
            this.wmMiniParams.format = -2;
            this.wmMiniParams.flags = 552;
            this.wmMiniParams.gravity = 51;
            this.wmMiniParams.width = -1;
            this.wmMiniParams.height = -1;
        }
        return this.wmMiniParams;
    }
    
    public WindowManager.LayoutParams getFloatBtnLayoutParams() {
        if (this.wmFloatBtnParams == null) {
            this.wmFloatBtnParams = new WindowManager.LayoutParams();
            this.wmFloatBtnParams.type = 2007;
            this.wmFloatBtnParams.format = -2;
            this.wmFloatBtnParams.flags = 552;
            this.wmFloatBtnParams.gravity = 51;
            this.wmFloatBtnParams.width = 100;
            this.wmFloatBtnParams.height = 100;
            this.wmFloatBtnParams.x = 340;
            this.wmFloatBtnParams.y = 0;
        }
        return this.wmFloatBtnParams;
    }
    
    public WindowManager.LayoutParams getLayoutParams() {
        if (this.wmParams == null) {
            this.wmParams = new WindowManager.LayoutParams();
            this.wmParams.type = 2007;
            this.wmParams.format = -2;
            this.wmParams.flags = 552;
            this.wmParams.gravity = 51;
            this.wmParams.width = 340;
            this.wmParams.height = 275;
        }
        return this.wmParams;
    }
    
    public WindowManager getWindowManagerInstence() {
        if (this.wm == null) {
            this.wm = (WindowManager)this.getSystemService("window");
        }
        return this.wm;
    }
    
    public void onCreate() {
        super.onCreate();
        MyApp.app = this;
        MyApp.activityList = new LinkedList<Activity>();
//        CrashHandler.getInstance().init(this.getApplicationContext());
    }
}
