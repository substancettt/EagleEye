package com.wellshang.eagleeye.minishow;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.wellshang.eagleeye.*;
import com.wellshang.eagleeye.main.*;
import com.wellshang.eagleeye.media.*;
import com.wellshang.eagleeye.receivers.*;

public class MiniFireEye extends Activity
{
    private static boolean CODE_DEBUG = false;
    private static ImageView CarReversingScale;
    private static SeekBar CarReversingSeekBar;
    private static String TAG;
    public static final int UPDATE_RECORDIND_FLAG = 10086;
    public static boolean isOpen;
    public static boolean isOpenFlag;
    public static ViewGroup layoutView;
    private static ImageButton mPreviewSwitch;
    private static TextView mRecordingFlag;
    private static SurfaceView mSurfaceView;
    private int delete_X;
    private int delete_Y;
    private GestureDetector detector;
    private long firClick;
    private int mAsternFlag;
    private FireEyeConfig mConfig;
    private int mFirstSensorID;
    private int mFlickerFlags;
    private Handler mHandler;
    private SurfaceHolder mHolder;
    private CameraRecorder mRecorder;
    private int mSensorID;
    private Point mSize;
    private View.OnTouchListener mTouchListener;
    private int mTouchStartX;
    private int mTouchStartY;
    private int mViewMove;
    SurfaceHolder.Callback miniCallback;
    private WindowManager.LayoutParams params;
    private GestureDetector.SimpleOnGestureListener sListener;
    private int save_Recordmode;
    private boolean save_Switchpreview;
    private long secClick;
    private WindowManager wm;
    
    static {
        MiniFireEye.TAG = "MiniFireEye";
        MiniFireEye.CODE_DEBUG = false;
        MiniFireEye.isOpen = false;
        MiniFireEye.isOpenFlag = false;
    }
    
    public MiniFireEye() {
        this.mSensorID = 0;
        this.delete_X = -120;
        this.delete_Y = 280;
        this.mSize = new Point(800, 480);
        this.firClick = 0L;
        this.secClick = 0L;
        this.mFirstSensorID = 0;
        this.mRecorder = null;
        this.mViewMove = 0;
        this.mTouchStartX = 0;
        this.mTouchStartY = 0;
        this.save_Recordmode = -1;
        this.save_Switchpreview = false;
        this.mAsternFlag = -1;
        this.miniCallback = (SurfaceHolder.Callback)new SurfaceHolder.Callback() {
            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (surfaceHolder.getSurface() == null) {
                    Log.d(MiniFireEye.TAG, "holder.getSurface() == null");
                }
                else if (MiniFireEye.this.mHolder != surfaceHolder) {
                    MiniFireEye.this.mHolder = surfaceHolder;
                    if (MiniFireEye.this.getIntent().getStringExtra("Carversing") == null || "".equals(MiniFireEye.this.getIntent().getStringExtra("Carversing"))) {
                        MiniFireEye.this.miniPreview(true);
                        return;
                    }
                    if (MiniFireEye.this.getIntent().getStringExtra("Carversing").equals("Carversing")) {
                        MiniFireEye.this.startMiniCarReversing(true);
                    }
                }
            }
            
            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
                if (MiniFireEye.CODE_DEBUG) {
                    Log.d(MiniFireEye.TAG, "mSurfaceView surfaceCreated");
                }
            }
            
            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                if (MiniFireEye.CODE_DEBUG) {
                    Log.d(MiniFireEye.TAG, "mSurfaceView surfaceDestroyed");
                }
                if (MiniFireEye.this.mHolder != null) {
                    MiniFireEye.this.mHolder = null;
                    if (FireEye.fSurfaceViewStatus()) {
                        MiniFireEye.this.miniPreview(false);
                    }
                }
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                if (message.what == 10086) {
                    MiniFireEye.this.updateRecordingFlag();
                }
            }
        };
        this.mFlickerFlags = 0;
    }
    
    public static void Exit() {
        if (MiniFireEye.isOpen) {
            MyApp.getInstance().getWindowManagerInstence().removeView((View)MiniFireEye.layoutView);
            MiniFireEye.isOpen = false;
        }
    }
    
    private void ShowPreviewSwitch() {
        (MiniFireEye.mPreviewSwitch = (ImageButton)MiniFireEye.layoutView.findViewById(2131361832)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (MiniFireEye.this.mSensorID == MiniFireEye.this.mConfig.FrontSensorID) {
                    MiniFireEye.this.miniPreview(false);
                    MiniFireEye.this.mSensorID = MiniFireEye.this.mConfig.BackSensorID;
                    MiniFireEye.this.miniPreview(true);
                    return;
                }
                MiniFireEye.this.miniPreview(false);
                MiniFireEye.this.mSensorID = MiniFireEye.this.mConfig.FrontSensorID;
                MiniFireEye.this.miniPreview(true);
            }
        });
    }
    
    public static void initWindow() {
        if (MiniFireEye.isOpen) {
            return;
        }
        MyApp.getInstance().getWindowManagerInstence().addView((View)MiniFireEye.layoutView, (ViewGroup.LayoutParams)MyApp.getInstance().getLayoutParams());
        MiniFireEye.isOpen = true;
        MiniFireEye.isOpenFlag = true;
    }
    
    private void miniPreview(final boolean b) {
        if (b) {
            this.mRecorder.stopPreview(this.mSensorID);
            this.mRecorder.setPreviewDisplay(this.mHolder, this.mSensorID);
            this.mRecorder.startPreview(this.mSensorID);
            return;
        }
        this.mRecorder.stopPreview(this.mSensorID);
        this.mRecorder.setPreviewDisplay(null, this.mSensorID);
    }
    
    @SuppressWarnings("deprecation")
    private void notifyMiniFireEye() {
        final NotificationManager notificationManager = (NotificationManager)this.getSystemService("notification");
        final PendingIntent activity = PendingIntent.getActivity((Context)this, 0, new Intent((Context)this, (Class<?>)MiniFireEye.class), 0);
        final Notification notification = new Notification();
        notification.flags |= 0x28;
//        final NotificationManager notificationManager2 = (NotificationManager)this.getSystemService("notification");
        notification.icon = 2130837599;
        notification.setLatestEventInfo((Context)this, (CharSequence)"FireEye", (CharSequence)"Mini Preview", activity);
        notificationManager.notify(1001, notification);
    }
    
    private void setListener() {
        MiniFireEye.CarReversingSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 480 - n * 5);
                if (n > 70) {
                    final RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 130);
                    layoutParams2.topMargin = 350;
                    MiniFireEye.this.mConfig.setCarReversingScaleLayoutParams(350);
                    MiniFireEye.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
                    return;
                }
                layoutParams.topMargin = n * 5;
                MiniFireEye.this.mConfig.setCarReversingScaleLayoutParams(n * 5);
                MiniFireEye.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
            }
        });
        this.sListener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(final MotionEvent motionEvent) {
                return true;
            }
            
            public boolean onDown(final MotionEvent motionEvent) {
                return true;
            }
            
            public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                if (motionEvent2.getRawX() - motionEvent.getRawX() < 0.0f && motionEvent2.getRawY() - motionEvent.getRawY() > 0.0f && n < -1000.0f && n2 > 500.0f) {
                    MiniFireEye.Exit();
                    MiniFireEye.this.notifyMiniFireEye();
                    FireEye.showFloatBtn();
                }
                return true;
            }
            
            public boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                return super.onScroll(motionEvent, motionEvent2, n, n2);
            }
            
            public boolean onSingleTapUp(final MotionEvent motionEvent) {
                return true;
            }
        };
        this.mTouchListener = (View.OnTouchListener)new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MiniFireEye.this.mViewMove = 0;
                    MiniFireEye.this.mTouchStartX = (int)(motionEvent.getRawX() - MyApp.getInstance().getLayoutParams().x);
                    MiniFireEye.this.mTouchStartY = (int)(motionEvent.getRawY() - MyApp.getInstance().getLayoutParams().y);
                    MiniFireEye.this.firClick = System.currentTimeMillis();
                }
                else if (motionEvent.getAction() == 1) {
                    if (MyApp.getInstance().getLayoutParams().x < MiniFireEye.this.delete_X && MyApp.getInstance().getLayoutParams().y > MiniFireEye.this.delete_Y) {
                        MiniFireEye.Exit();
                        MyApp.getInstance().getLayoutParams().x = 0;
                        MyApp.getInstance().getLayoutParams().y = 0;
                        MiniFireEye.this.notifyMiniFireEye();
                        FireEye.showFloatBtn();
                        return true;
                    }
                    if (MyApp.getInstance().getLayoutParams().x < 0) {
                        MyApp.getInstance().getLayoutParams().x = 0;
                    }
                    else if (MyApp.getInstance().getLayoutParams().x > MiniFireEye.this.mSize.x - MyApp.getInstance().getLayoutParams().width) {
                        MyApp.getInstance().getLayoutParams().x = MiniFireEye.this.mSize.x - MyApp.getInstance().getLayoutParams().width;
                    }
                    if (MyApp.getInstance().getLayoutParams().y < 0) {
                        MyApp.getInstance().getLayoutParams().y = 0;
                    }
                    else if (MyApp.getInstance().getLayoutParams().y > MiniFireEye.this.mSize.y - MyApp.getInstance().getLayoutParams().height) {
                        MyApp.getInstance().getLayoutParams().y = MiniFireEye.this.mSize.y - MyApp.getInstance().getLayoutParams().height;
                    }
                    MyApp.getInstance().getWindowManagerInstence().updateViewLayout((View)MiniFireEye.layoutView, (ViewGroup.LayoutParams)MyApp.getInstance().getLayoutParams());
                    if (MiniFireEye.this.firClick - MiniFireEye.this.secClick < 300L) {
                        MiniFireEye.Exit();
                        final Intent intent = new Intent();
                        intent.setClass((Context)MiniFireEye.this, (Class<?>)FireEye.class);
                        MiniFireEye.this.startActivity(intent);
                        MiniFireEye.this.finish();
                    }
                    MiniFireEye.this.secClick = MiniFireEye.this.firClick;
                    return true;
                }
                else if (motionEvent.getAction() == 2) {
                    if (MiniFireEye.this.mViewMove == 0) {
                        MiniFireEye.this.mViewMove = 1;
                    }
                    MyApp.getInstance().getLayoutParams().x = (int)motionEvent.getRawX() - MiniFireEye.this.mTouchStartX;
                    MyApp.getInstance().getLayoutParams().y = (int)motionEvent.getRawY() - MiniFireEye.this.mTouchStartY;
                    MyApp.getInstance().getWindowManagerInstence().updateViewLayout((View)MiniFireEye.layoutView, (ViewGroup.LayoutParams)MyApp.getInstance().getLayoutParams());
                    return true;
                }
                return true;
            }
        };
        this.detector = new GestureDetector(this.getApplicationContext(), (GestureDetector.OnGestureListener)this.sListener);
        MiniFireEye.mSurfaceView.setOnTouchListener(this.mTouchListener);
    }
    
    public void ShowRecordingLabels(final boolean b) {
        if (b) {
            MiniFireEye.mRecordingFlag = (TextView)MiniFireEye.layoutView.findViewById(2131361833);
            this.mFlickerFlags = 0;
            MiniFireEye.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837576, 0, 0, 0);
            MiniFireEye.mRecordingFlag.setVisibility(0);
            this.mHandler.sendEmptyMessageDelayed(10086, 500L);
            return;
        }
        MiniFireEye.mRecordingFlag.setVisibility(8);
        this.mFlickerFlags = -1;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (!FireEye.float_btn_hidden_flag) {
            FireEye.Exit();
        }
        ((NotificationManager)this.getSystemService("notification")).cancel(1001);
        if (MiniFireEye.CODE_DEBUG) {
            Log.d(MiniFireEye.TAG, "onCreate");
        }
        if (!MiniFireEye.isOpen) {
            this.getWindowManager().getDefaultDisplay().getSize(this.mSize);
            MiniFireEye.layoutView = (ViewGroup)View.inflate(this.getApplicationContext(), 2130903052, (ViewGroup)null);
            this.mRecorder = FireEyeShare.getCameraRecorder();
            this.mConfig = FireEyeShare.getFireEyeConfig(this.getApplicationContext());
            this.mSensorID = this.mConfig.FrontSensorID;
            this.mFirstSensorID = this.mSensorID;
            MiniFireEye.mSurfaceView = (SurfaceView)MiniFireEye.layoutView.findViewById(2131361831);
            MiniFireEye.CarReversingScale = (ImageView)MiniFireEye.layoutView.findViewById(2131361834);
            MiniFireEye.CarReversingSeekBar = (SeekBar)MiniFireEye.layoutView.findViewById(2131361835);
            MiniFireEye.mSurfaceView.getHolder().addCallback(this.miniCallback);
            this.setListener();
            this.mRecorder.setPreviewDisplay(MiniFireEye.mSurfaceView.getHolder(), this.mConfig.FrontSensorID);
            this.ShowPreviewSwitch();
            if (FireEyeAction.getRecordingStatus()) {
                this.ShowRecordingLabels(true);
            }
            if (this.mConfig.getRecordModeValue() == 0) {
                MiniFireEye.mPreviewSwitch.setVisibility(0);
            }
            else {
                MiniFireEye.mPreviewSwitch.setVisibility(8);
            }
            initWindow();
        }
        MyApp.getInstance().setMiniFireEye(this);
        if (FireEyeTTS.isMiniFireEyeFirstStart && FireEye.CarReversingFlag == -1) {
            FireEyeTTS.speakChinese(FireEyeTTS.MINI_FIRE_EYE_OPEN);
            FireEyeTTS.isMiniFireEyeFirstStart = false;
        }
        this.finish();
    }
    
    public void onPause() {
        super.onPause();
        if (((ActivityManager)this.getSystemService("activity")).getRunningTasks(2).get(0).topActivity.getPackageName().equalsIgnoreCase("com.android.launcher")) {
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.finish();
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void startMiniCarReversing(final boolean b) {
        MiniFireEye.mSurfaceView.setOnTouchListener((View.OnTouchListener)null);
        this.sendBroadcast(new Intent("android.intent.action.HIDE_STATUS_BAR"));
        this.save_Recordmode = this.mConfig.getRecordModeValue();
        this.save_Switchpreview = this.mConfig.getSwitchPreview();
        this.params = MyApp.getInstance().getCarReversingMiniLayoutParams();
        this.wm = MyApp.getInstance().getWindowManagerInstence();
        MiniFireEye.CarReversingScale.setVisibility(0);
        MiniFireEye.CarReversingSeekBar.setVisibility(0);
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 480 - this.mConfig.getCarReversingScaleLayoutParams());
        layoutParams.topMargin = this.mConfig.getCarReversingScaleLayoutParams();
        MiniFireEye.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        MiniFireEye.mPreviewSwitch.setVisibility(8);
        if (this.save_Recordmode == 0) {
            if (this.save_Switchpreview) {
                if (this.mFirstSensorID != this.mSensorID) {
                    this.miniPreview(false);
                    this.mSensorID = this.mConfig.FrontSensorID;
                    this.miniPreview(true);
                    this.mAsternFlag = 1;
                }
            }
            else if (this.mFirstSensorID == this.mSensorID) {
                this.miniPreview(false);
                this.mSensorID = this.mConfig.BackSensorID;
                this.miniPreview(true);
                this.mAsternFlag = 2;
            }
        }
        else if (this.save_Recordmode == 1) {
            this.mRecorder.stopPreview(this.mConfig.FrontSensorID);
            this.mRecorder.setPreviewDisplay(null, this.mConfig.FrontSensorID);
            this.mRecorder.setVideoQuality(this.mConfig.mBkVideoQuality, this.mConfig.BackSensorID);
            this.mRecorder.startCamera(this.mConfig.BackSensorID);
            this.mRecorder.setPreviewDisplay(this.mHolder, this.mConfig.BackSensorID);
            this.mRecorder.startPreview(this.mConfig.BackSensorID);
            this.mAsternFlag = 3;
        }
        else if (this.save_Recordmode == 2 && b) {
            this.miniPreview(true);
            this.mAsternFlag = 4;
        }
        this.wm.updateViewLayout((View)MiniFireEye.layoutView, (ViewGroup.LayoutParams)this.params);
    }
    
    public void stopMiniCarReversing(final boolean b) {
        MiniFireEye.mSurfaceView.setOnTouchListener(this.mTouchListener);
        if (!((ActivityManager)this.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName().equals("com.daxun.launcher.activity.MainActivity")) {
            this.sendBroadcast(new Intent("android.intent.action.DISPLAY_STATUS_BAR"));
        }
        if (this.mAsternFlag == 1) {
            this.miniPreview(false);
            this.mSensorID = this.mConfig.BackSensorID;
            if (b) {
                MiniFireEye.mSurfaceView = null;
            }
            else {
                this.miniPreview(true);
            }
        }
        else if (this.mAsternFlag == 2) {
            this.miniPreview(false);
            this.mSensorID = this.mConfig.FrontSensorID;
            if (b) {
                MiniFireEye.mSurfaceView = null;
            }
            else {
                this.miniPreview(true);
            }
        }
        else if (this.mAsternFlag == 3) {
            this.mRecorder.stopPreview(this.mConfig.BackSensorID);
            this.mRecorder.setPreviewDisplay(null, this.mConfig.BackSensorID);
            this.mRecorder.stopCamera(this.mConfig.BackSensorID);
            if (b) {
                MiniFireEye.mSurfaceView = null;
            }
            else {
                this.mRecorder.setPreviewDisplay(this.mHolder, this.mConfig.FrontSensorID);
                this.mRecorder.startPreview(this.mConfig.FrontSensorID);
            }
        }
        else if (this.mAsternFlag == 4) {
            this.miniPreview(false);
        }
        this.mAsternFlag = -1;
        this.params = MyApp.getInstance().getLayoutParams();
        this.wm = MyApp.getInstance().getWindowManagerInstence();
        MiniFireEye.CarReversingScale.setVisibility(8);
        MiniFireEye.CarReversingSeekBar.setVisibility(8);
        if (this.mConfig.getRecordModeValue() == 0) {
            MiniFireEye.mPreviewSwitch.setVisibility(0);
        }
        if (!b) {
            this.wm.updateViewLayout((View)MiniFireEye.layoutView, (ViewGroup.LayoutParams)this.params);
        }
    }
    
    public void updateRecordingFlag() {
        if (this.mFlickerFlags == 0) {
            this.mFlickerFlags = 1;
            MiniFireEye.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837575, 0, 0, 0);
        }
        else {
            this.mFlickerFlags = 0;
            MiniFireEye.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837576, 0, 0, 0);
        }
        if (this.mFlickerFlags != -1) {
            this.mHandler.sendEmptyMessageDelayed(10086, 500L);
        }
    }
}
