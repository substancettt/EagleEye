package com.wellshang.eagleeye;

import android.graphics.*;
import android.view.*;
import android.app.*;

public class FireEyeParamsShare
{
    public int mCarReversingScale;
    public int mFloatBtnHeight;
    public int mFloatBtnWidth;
    public int mFloatBtnX;
    public int mListSwitchTouchDisX;
    public int mMiniDelete_X;
    public int mMiniDelete_Y;
    public int mMiniHeight;
    public int mMiniWidth;
    public int mPIPBounds;
    public int mPIPDelete_X;
    public int mPIPDelete_Y;
    public int mPIPLargeHeight;
    public int mPIPLargeWidth;
    public int mPIPSmallHeight;
    public int mPIPSmallWidth;
    public Point mSize;
    public int mSwitchModeY;
    public int mSwitchPreviewXY;
    private WindowManager wm;
    
    public FireEyeParamsShare(final Activity activity) {
        this.wm = activity.getWindowManager();
        this.initParams();
    }
    
    private void initParams() {
        this.mSize = new Point(800, 480);
        this.mPIPLargeWidth = 600;
        this.mPIPLargeHeight = 400;
        this.mPIPSmallWidth = 320;
        this.mPIPSmallHeight = 240;
        this.mPIPBounds = 480;
        this.mPIPDelete_X = -120;
        this.mPIPDelete_Y = 280;
        this.mCarReversingScale = 5;
        this.mSwitchModeY = 54;
        this.mSwitchPreviewXY = 100;
        this.mListSwitchTouchDisX = 80;
        this.mMiniDelete_X = -120;
        this.mMiniDelete_Y = 280;
        this.mMiniWidth = 340;
        this.mMiniHeight = 275;
        this.mFloatBtnWidth = 100;
        this.mFloatBtnHeight = 100;
        this.mFloatBtnX = 340;
    }
}
