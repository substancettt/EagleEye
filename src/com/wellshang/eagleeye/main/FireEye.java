package com.wellshang.eagleeye.main;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.graphics.*;
import android.hardware.*;
import android.location.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.os.storage.*;
import android.provider.*;
import android.speech.tts.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import com.wellshang.eagleeye.*;
import com.wellshang.eagleeye.adapter.ListSwitchAdapterFrist;
import com.wellshang.eagleeye.adapter.*;
import com.wellshang.eagleeye.media.*;
import com.wellshang.eagleeye.minishow.*;
import com.wellshang.eagleeye.receivers.*;
import com.wellshang.eagleeye.receivers.FireEyeAction.FireEyeActionListener;
import com.wellshang.eagleeye.storage.*;

public class FireEye extends Activity implements FireEyeActionListener, TextToSpeech.OnInitListener
{
//    private static final int AUTO_START_RECORDING = 9;
//    private static final int CAR_REVERSING = 12;
//    private static final String CLASS_NAME = "com.wellshang.eagleeye.main.EagleEye";
    private static boolean CODE_DEBUG = false;
    public static int CarReversingFlag = 0;
//    private static final String DA_XUN_LAUNCHER = "com.daxun.launcher.activity.MainActivity";
    public static final String DX_FILE_SYSTEM_CLOSE = "com.daxun.dx_file_system_close";
    public static final String DX_FILE_SYSTEM_OPEN = "com.daxun.dx_file_system_open";
//    private static final int EXTSD_MOUNTED = 100;
//    private static final int EXTSD_UNMOUNTED = 101;
//    private static final String FIREEYE_HOME = "com.fireeye.action.home";
    public static final String FIRE_EYE_START_RECORDING = "com.softwinner.fireeye.start.recording";
    public static final String FIRE_EYE_STOP_RECORDING = "com.softwinner.fireeye.stop.recording";
//    private static final int HIDEN_SETTING_LAYOUT = 20000;
//    private static final int HIDEN_SHORTCUT_LAYOUT = 100002;
//    private static final int LAST_START_RECORDING = 1000;
//    private static final int LAST_STOP_RECORDING = 1001;
//    private static final String MINI_CLASS_NAME = "com.softwinner.fireeye.minishow.MiniFireEye";
    public static final int MINI_STATUS_SHOW_ID = 1001;
//    private static final int MY_DATA_CHECK_CODE = 0;
//    private static final int NO_EXTSD_SHOW = 11;
//    private static final int NO_SPACE_SHOW = 13;
//    private static final int POWER_OFF_DELAY = 10;
//    private static final int SHOW_SETTING_LAYOUT = 10000;
//    private static final int SHOW_SHORTCUT_LAYOUT = 100001;
//    private static final int STORAGE_DISK_IS_FULL = 7;
//    private static final int STORAGE_IMAGE_SUCCEED = 6;
    private static String TAG;
//    private static final int UPDATE_MOTION_DETECT = 8;
//    private static final int UPDATE_RECORD_TIME = 4;
//    private static final int UPDATE_STORAGE_SPACE = 5;
    private static SurfaceHolder bHolder;
    private static SurfaceView bSurfaceView;
    private static SurfaceHolder fHolder;
    private static SurfaceView fSurfaceView;
    private static Button float_btn;
    public static boolean float_btn_hidden_flag;
    private static View float_button;
    private static boolean isAddViewFlag;
    public static int[] mMarkIndex;
    public static int mMaxIndex;
    private static NotificationManager mNotificationManager;
    private static CameraRecorder mRecorder;
    private static WindowManager wm;
    private static WindowManager.LayoutParams wmParams;
    private ImageView CarReversingScale;
    private SeekBar CarReversingSeekBar;
    SensorEventListener GsensorListener;
    private int LastDet;
    ImageButton ShortcutMute;
    ImageButton ShortcutPip;
    ImageButton ShortcutPreview;
    private int StartDet;
    float X_lateral_s;
    float Y_longitudinal_s;
    float Z_vertical_s;
    SurfaceHolder.Callback backCallback;
//    private int bottom;
    private ImageButton camera_setting_btn;
    private ImageButton camera_switch_btn;
//    private String className;
    private Location currentLocation;
    private boolean fileLockToastFlag;
    private long firClick;
    private long firClickFloat;
    SurfaceHolder.Callback frontCallback;
    private int hasBackCamera;
    private boolean isFireEyeExit;
    private boolean isFloatBtnOpen;
    private boolean isListSwitchFristViewSelected;
    private boolean isMiniOpen;
    private boolean isSleep;
    private CameraRecorder.PictureCallback jpegCallback;
//    private int lastX;
//    private int lastY;
//    private int left;
    private ListSwitchAdapterFrist listItemAdapterFrist;
    private ListSwitchAdapterSecond listItemAdapterSecond;
    private GestureDetector listSwitchDetector;
    private View listSwitchItemFrist;
    private GridView listSwitchItemGridViewFrist;
    private GridView listSwitchItemGridViewSecond;
    private View listSwitchItemSecond;
    private ImageView listSwitchItemViewFrist;
    private ImageView listSwitchItemViewSecond;
    private View.OnTouchListener listSwitchOnTouchListener;
    private GestureDetector.SimpleOnGestureListener listSwitchSimpleOnGestureListener;
    LocationListener locationListener;
    private LocationManager locationManager;
    private long mAutoPowerOffRepeatTime;
    private long mAutoPowerOffStartTime;
    private boolean mBackPreviewing;
    private boolean mBrowseStatus;
    private boolean mBrowseStatusFlag;
//    private CameraSound mCameraSound;
    private boolean mCarReverse;
    private FireEyeConfig mConfig;
    private ContentResolver mContentResolver;
    private Uri mCurrentImageUri;
    private Uri mCurrentVideoUri;
    private boolean mExternalStorage;
    private int mFlickerFlags;
    private boolean mFrontPreviewing;
    private SensorManager mGsensor;
    private final Handler mHandler;
    private int mMenuFlags;
    private ListView mMenuListSet;
    private int mMenuListSetIndex;
    private ViewFlipper mMenuListSwitch;
    private View mMenuSet;
    private ImageView mMenuSetView;
    private View mMenuSwitch;
    private int mMotionCnt;
    private boolean mPowerStatus;
    private TextView mRecordingFlag;
    private int mRecordingRepeatTime;
    private long mRecordingStartTime;
    private boolean mRecordingStatus;
    private TextView mRecordingTime;
    private ImageButton mReviewImage;
    private ScaleGestureDetector mScaleGestureDetector;
    private ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener;
    private Point mSize;
    private ImageButton mStartWork;
    private ImageButton mStopWorkBtn;
    private boolean mStorageFlag;
    private long mStorageSpace;
    private View mSubMenuSet;
    private ImageButton mSwitchMode;
    private boolean mTakeImageStatus;
    private long mTakePictureTime;
    private int mTouchEndX;
    private int mTouchEndY;
    private View.OnTouchListener mTouchListener;
    private int mTouchStartX;
    private int mTouchStartX_s;
    private int mTouchStartY;
    private int mTouchStartY_s;
    private AlertDialog noDiskDialog;
    private AlertDialog noSpaceDialog;
    private int old_value;
    private int old_value2;
    private boolean openMotionDetect;
//    private String packageName;
    private ViewGroup.LayoutParams params;
    private PowerManager pm;
    private BroadcastReceiver receiver;
    private ContentResolver resolver;
    private MediaMetadataRetriever retriever;
//    private int right;
    private long secClick;
    private long secClickFloat;
    private View settingLayout;
    private View shortCutLayout;
    private AlertDialog showAutoPowerOffDialog;
    private int showAutoPowerOffDialogCount;
    private AlertDialog showDelayShutDownDialog;
    private boolean showSettingLayoutFlag;
    private boolean showShortCutFlag;
    private Animation showShortcutAnimation;
    private CameraRecorder.ShutterCallback shutterCallback;
    private double speedKMH;
    private double speedMS;
    private ToneGenerator tone;
//    private int top;
//    private int value;
    private int value2;
    private PowerManager.WakeLock wl;
    
    static {
        FireEye.TAG = "FireEye";
        FireEye.CODE_DEBUG = false;
        FireEye.mNotificationManager = null;
        FireEye.mMaxIndex = 0;
        FireEye.mMarkIndex = new int[30];
        FireEye.mRecorder = null;
        FireEye.float_btn_hidden_flag = true;
        FireEye.isAddViewFlag = false;
        FireEye.CarReversingFlag = -1;
    }
    
    public FireEye() {
        this.mMotionCnt = 0;
        this.X_lateral_s = 0.0f;
        this.Y_longitudinal_s = 0.0f;
        this.Z_vertical_s = 0.0f;
        this.mExternalStorage = false;
        this.mFrontPreviewing = false;
        this.mBackPreviewing = false;
        this.mRecordingStatus = false;
        this.mTakeImageStatus = false;
        this.mBrowseStatus = false;
        this.mPowerStatus = false;
        this.mMenuSetView = null;
        this.mCurrentImageUri = null;
        this.mCurrentVideoUri = null;
        this.mFlickerFlags = 0;
        this.mMenuFlags = 0;
        this.mMenuListSetIndex = 0;
        this.mMenuSet = null;
        this.mSubMenuSet = null;
        this.mMenuSwitch = null;
        this.mMenuListSet = null;
        this.mMenuListSwitch = null;
        this.listSwitchItemFrist = null;
        this.listSwitchItemSecond = null;
        this.listSwitchItemGridViewFrist = null;
        this.listSwitchItemGridViewSecond = null;
        this.listItemAdapterFrist = null;
        this.listItemAdapterSecond = null;
        this.listSwitchItemViewFrist = null;
        this.listSwitchItemViewSecond = null;
        this.isListSwitchFristViewSelected = true;
        this.mTouchStartX = 0;
        this.mTouchEndX = 0;
        this.mTouchStartY = 0;
        this.mTouchEndY = 0;
        this.firClick = 0L;
        this.secClick = 0L;
        this.mSize = new Point(800, 480);
        this.mScaleGestureDetector = null;
        this.mScaleGestureListener = null;
        this.showSettingLayoutFlag = true;
        this.showShortCutFlag = true;
        this.isSleep = false;
        this.mHandler = new MainHandler();
        this.isFireEyeExit = false;
        this.fileLockToastFlag = false;
        this.frontCallback = (SurfaceHolder.Callback)new SurfaceHolder.Callback() {
            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "fSurfaceView preview size: width = " + n2 + ", height = " + n3 + ", mRecorder = " + FireEye.mRecorder);
                }
                if (surfaceHolder.getSurface() == null) {
                    Log.d(FireEye.TAG, "holder.getSurface() == null");
                }
                else if (FireEye.mRecorder != null && FireEye.fHolder != surfaceHolder) {
                    FireEye.fHolder = surfaceHolder;
                    FireEye.this.frontPreview(true);
                }
            }
            
            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "fSurfaceView surfaceCreated");
                }
            }
            
            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "fSurfaceView surfaceDestroyed");
                }
                if (FireEye.fHolder != null) {
                    FireEye.fHolder = null;
                }
            }
        };
        this.backCallback = (SurfaceHolder.Callback)new SurfaceHolder.Callback() {
            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "bSurfaceView preview size: width = " + n2 + ", height = " + n3 + ", mRecorder = " + FireEye.mRecorder);
                }
                if (surfaceHolder.getSurface() == null) {
                    Log.d(FireEye.TAG, "holder.getSurface() == null");
                }
                else if (FireEye.mRecorder != null && FireEye.bHolder != surfaceHolder) {
                    FireEye.bHolder = surfaceHolder;
                    FireEye.this.backPreview(true);
                }
            }
            
            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "bSurfaceView surfaceCreated");
                }
            }
            
            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "bSurfaceView surfaceDestroyed");
                }
                if (FireEye.bHolder != null) {
                    FireEye.bHolder = null;
                }
            }
        };
        this.locationListener = (LocationListener)new LocationListener() {
            public void onLocationChanged(final Location location) {
                FireEye.this.updateLocation(location);
            }
            
            public void onProviderDisabled(final String s) {
            }
            
            public void onProviderEnabled(final String s) {
            }
            
            public void onStatusChanged(final String s, final int n, final Bundle bundle) {
            }
        };
        this.GsensorListener = (SensorEventListener)new SensorEventListener() {
            public void onAccuracyChanged(final Sensor sensor, final int n) {
            }
            
            public void onSensorChanged(final SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == 1) {
                    final float x_lateral_s = sensorEvent.values[0];
                    final float y_longitudinal_s = sensorEvent.values[1];
                    final float z_vertical_s = sensorEvent.values[2];
                    if (FireEye.this.mConfig.getAutoPowerOff() && (Math.abs(x_lateral_s - FireEye.this.X_lateral_s) > 1.5 || Math.abs(y_longitudinal_s - FireEye.this.Y_longitudinal_s) > 1.5 || Math.abs(z_vertical_s - FireEye.this.Z_vertical_s) > 1.5)) {
                        Log.i(FireEye.TAG, "abs < 1.0 get it\n");
                        FireEye.this.X_lateral_s = x_lateral_s;
                        FireEye.this.Y_longitudinal_s = y_longitudinal_s;
                        FireEye.this.Z_vertical_s = z_vertical_s;
                        FireEye.this.mAutoPowerOffStartTime = System.currentTimeMillis();
                        FireEye.this.showAutoPowerOffDialogCount = 0;
                    }
                    if (FireEye.this.mRecordingStatus && (x_lateral_s > FireEye.this.mConfig.getSensorNumber() || z_vertical_s > FireEye.this.mConfig.getSensorNumber())) {
                        FileStorage.SetFileLock(true);
                        FireEye.this.showFileLockToastText();
                    }
                }
            }
        };
        this.params = null;
        this.mTouchListener = (View.OnTouchListener)new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    FireEye.this.mTouchStartX = (int)(motionEvent.getRawX() - FireEye.wmParams.x);
                    FireEye.this.mTouchStartY = (int)(motionEvent.getRawY() - FireEye.wmParams.y);
                    FireEye.this.firClickFloat = System.currentTimeMillis();
                }
                else if (motionEvent.getAction() == 1) {
                    if (FireEye.wmParams.x < 0) {
                        FireEye.wmParams.x = 0;
                    }
                    else if (FireEye.wmParams.x > FireEye.this.mSize.x - FireEye.wmParams.width) {
                        FireEye.wmParams.x = FireEye.this.mSize.x - FireEye.wmParams.width;
                    }
                    if (FireEye.wmParams.y < 0) {
                        FireEye.wmParams.y = 0;
                    }
                    else if (FireEye.wmParams.y > FireEye.this.mSize.y - FireEye.wmParams.height) {
                        FireEye.wmParams.y = FireEye.this.mSize.y - FireEye.wmParams.height;
                    }
                    FireEye.wm.updateViewLayout(FireEye.float_button, (ViewGroup.LayoutParams)FireEye.wmParams);
                    FireEye.this.secClickFloat = System.currentTimeMillis();
                    if (FireEye.this.secClickFloat - FireEye.this.firClickFloat < 300L) {
                        FireEye.this.startActivity(new Intent(FireEye.this.getApplicationContext(), (Class<?>)MiniFireEye.class));
                        return true;
                    }
                }
                else if (motionEvent.getAction() == 2) {
                    FireEye.wmParams.x = (int)motionEvent.getRawX() - FireEye.this.mTouchStartX;
                    FireEye.wmParams.y = (int)motionEvent.getRawY() - FireEye.this.mTouchStartY;
                    FireEye.wm.updateViewLayout(FireEye.float_button, (ViewGroup.LayoutParams)FireEye.wmParams);
                    return true;
                }
                return true;
            }
        };
        this.mStorageFlag = true;
        this.hasBackCamera = 0;
        this.LastDet = 0;
        this.StartDet = 0;
        this.speedMS = 0.0;
        this.speedKMH = 0.0;
        this.shutterCallback = new CameraRecorder.ShutterCallback() {
            @Override
            public void onShutter() {
                if (FireEye.this.tone == null) {
                    FireEye.this.tone = new ToneGenerator(3, 100);
                }
                FireEye.this.tone.startTone(28);
            }
        };
        this.jpegCallback = new CameraRecorder.PictureCallback() {
            @Override
            public void onPictureTaken(final byte[] array, final int n) {
            }
        };
        this.resolver = null;
        this.retriever = null;
        this.mTouchStartX_s = 0;
        this.mTouchStartY_s = 0;
        this.openMotionDetect = false;
        this.showAutoPowerOffDialog = null;
        this.ShortcutMute = null;
        this.ShortcutPreview = null;
        this.ShortcutPip = null;
        this.mCarReverse = true;
        this.old_value = 1;
        this.old_value2 = 0;
        this.value2 = 0;
        this.isMiniOpen = false;
        this.isFloatBtnOpen = false;
        this.showAutoPowerOffDialogCount = 0;
        this.mBrowseStatusFlag = false;
        this.receiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                final String action = intent.getAction();
                if ("com.fireeye.action.home".equals(action)) {
                    if (!MiniFireEye.isOpen) {
                        FireEye.showFloatBtn();
                    }
                }
                else {
                    if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
                        FireEye.this.PowerShutDown();
                        return;
                    }
                    if ("com.daxun.dx_file_system_open".equals(action)) {
                        if (!FireEye.this.mBrowseStatusFlag && FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                            FireEye.this.SetMotionDetectStatus(false);
                        }
                    }
                    else if ("com.daxun.dx_file_system_close".equals(action)) {
                        if (!FireEye.this.mBrowseStatusFlag && FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                            FireEye.this.mHandler.removeMessages(9);
                            FireEye.this.SetMotionDetectStatus(true);
                        }
                        FireEye.this.mBrowseStatusFlag = false;
                    }
                }
            }
        };
    }
    
    private void AutoRecording() {
        this.StartRecording();
    }
    
    private void CarReversing() {
/*
        if (!this.mCarReverse) {
            return;
        }
        this.value = Gpio.readGpio('A', 0);
        if (this.value != this.old_value) {
            if (this.value == 0 && Gpio.readGpio('A', 7) == 0) {
                FireEye.CarReversingFlag = 0;
                if (!this.pm.isScreenOn()) {
                    FireEye.CarReversingFlag = 1;
                }
                (this.wl = this.pm.newWakeLock(268435482, FireEye.TAG)).acquire();
                final ActivityManager.RunningTaskInfo activityManager$RunningTaskInfo = ((ActivityManager)this.getSystemService("activity")).getRunningTasks(1).get(0);
                this.className = activityManager$RunningTaskInfo.topActivity.getClassName();
                this.packageName = activityManager$RunningTaskInfo.topActivity.getPackageName();
                FireEyeTTS.speakChinese(FireEyeTTS.CAR_REVERSING);
                if (!this.isSleep || this.className.equals("com.softwinner.fireeye.main.FireEye")) {
                    if (FireEye.CarReversingFlag == 0) {
                        this.mConfig.setCarRevsersing(true);
                    }
                    if (this.mMenuFlags != 0) {
                        this.CloseSettingMenu();
                    }
                    this.mHandler.sendEmptyMessage(20000);
                    this.mHandler.sendEmptyMessage(100002);
                    this.CarReversingScale.setVisibility(0);
                    this.CarReversingSeekBar.setVisibility(0);
                    final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 480 - this.mConfig.getCarReversingScaleLayoutParams());
                    layoutParams.topMargin = this.mConfig.getCarReversingScaleLayoutParams();
                    this.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
                    this.sendBroadcast(new Intent("android.intent.action.HIDE_STATUS_BAR"));
                }
                else if (this.isSleep) {
                    this.isMiniOpen = MiniFireEye.isOpen;
                    this.isFloatBtnOpen = !FireEye.float_btn_hidden_flag;
                    if (!MiniFireEye.isOpen) {
                        Exit();
                        final Intent intent = new Intent(this.getApplicationContext(), (Class)MiniFireEye.class);
                        intent.putExtra("Carversing", "Carversing");
                        this.startActivity(intent);
                    }
                    else {
                        MyApp.getInstance().getMiniFireEye().startMiniCarReversing(false);
                    }
                }
            }
            else {
                FireEye.CarReversingFlag = -1;
                if (!this.isSleep) {
                    this.sendBroadcast(new Intent("android.intent.action.DISPLAY_STATUS_BAR"));
                    this.CarReversingScale.setVisibility(8);
                    this.CarReversingSeekBar.setVisibility(8);
                    this.mConfig.setCarRevsersing(false);
                }
                else {
                    if (this.isMiniOpen) {
                        this.isMiniOpen = false;
                        if (!MiniFireEye.isOpen) {
                            this.startActivity(new Intent(this.getApplicationContext(), (Class)MiniFireEye.class));
                        }
                        MyApp.getInstance().getMiniFireEye().stopMiniCarReversing(this.isFloatBtnOpen);
                    }
                    if (this.isFloatBtnOpen) {
                        MyApp.getInstance().getMiniFireEye().stopMiniCarReversing(this.isFloatBtnOpen);
                        this.isFloatBtnOpen = false;
                        MiniFireEye.Exit();
                        showFloatBtn();
                    }
                }
            }
            this.old_value = this.value;
        }
        if (this.value2 != this.old_value2) {
            final boolean mRecordingStatus = this.mRecordingStatus;
            if (this.mRecordingStatus) {
                this.StopRecording();
                this.mStartWork.setBackgroundResource(2130837584);
            }
            if (this.value2 == 0) {
                this.mConfig.setRecordModeValue(1);
                this.mConfig.setPIPStatus(false);
            }
            else {
                this.mConfig.setRecordModeValue(0);
            }
            if (mRecordingStatus) {
                FireEyeTTS.speakChinese(FireEyeTTS.START_RECORDING);
                this.StartRecording();
            }
            this.old_value2 = this.value2;
        }
        int motionStatus = 0;
        if (this.mConfig.mMotionStatus == 1) {
            if (this.mConfig.getRecordModeValue() == 0) {
                motionStatus = FireEye.mRecorder.getMotionStatus(this.mConfig.FrontSensorID) + FireEye.mRecorder.getMotionStatus(this.mConfig.BackSensorID);
            }
            else {
                motionStatus = FireEye.mRecorder.getMotionStatus(this.mConfig.FrontSensorID);
            }
        }
        if (this.mConfig.getAutoPowerOff()) {
            if (motionStatus > 0) {
                this.mAutoPowerOffStartTime = System.currentTimeMillis();
                this.showAutoPowerOffDialogCount = 0;
            }
            final long n = System.currentTimeMillis() - this.mAutoPowerOffStartTime;
            this.mAutoPowerOffRepeatTime = this.mConfig.getAutoPowerOffTime();
            if (this.mAutoPowerOffRepeatTime - n <= 16000L && this.mAutoPowerOffRepeatTime - n >= 0L && 15500L < this.mAutoPowerOffRepeatTime - n && this.showAutoPowerOffDialogCount == 0) {
                FireEyeTTS.speakChinese(FireEyeTTS.WILL_AUTO_POWER_OFF);
                (this.wl = this.pm.newWakeLock(268435482, FireEye.TAG)).acquire();
            }
            if (this.mAutoPowerOffRepeatTime - n <= 10000L && this.mAutoPowerOffRepeatTime - n >= 0L) {
                if (this.showAutoPowerOffDialogCount % 2 == 0) {
                    if (!this.isSleep) {
                        this.showAutoPowerOffDialog("" + ((this.mAutoPowerOffRepeatTime - n) / 1000L + 1L));
                    }
                    else {
                        this.showAutoPowerOffToastText("" + ((this.mAutoPowerOffRepeatTime - n) / 1000L + 1L));
                    }
                }
                ++this.showAutoPowerOffDialogCount;
            }
            else if (this.showAutoPowerOffDialog != null) {
                this.showAutoPowerOffDialog.dismiss();
            }
            if (n >= this.mAutoPowerOffRepeatTime) {
                Log.i(FireEye.TAG, "will power off\n");
                this.PowerShutDown();
            }
        }
        else {
            this.mAutoPowerOffStartTime = System.currentTimeMillis();
            this.showAutoPowerOffDialogCount = 0;
        }
        this.mHandler.sendEmptyMessageDelayed(12, 500L);
*/
    }
    
    private void CloseSettingMenu() {
        if (this.mMenuFlags == 1) {
            this.mMenuSet.setVisibility(8);
            this.mMenuSet = null;
            this.mMenuFlags = 0;
        }
        else if (this.mMenuFlags == 2) {
            this.mSubMenuSet.setVisibility(8);
            this.mSubMenuSet = null;
            this.mMenuSet.setVisibility(8);
            this.mMenuSet = null;
            this.mMenuFlags = 0;
        }
        else if (this.mMenuFlags == 3) {
            this.mMenuSwitch.setVisibility(8);
            this.mMenuSwitch = null;
            this.mMenuFlags = 0;
        }
        this.mHandler.sendEmptyMessageDelayed(20000, 5000L);
        if (this.mMenuFlags == 0 && this.mConfig.mMotionStatus == 1 && this.mConfig.getWorkStatus() == 0) {
            this.SetMotionDetectStatus(this.openMotionDetect = true);
        }
    }
    
    public static void Exit() {
        if (FireEye.float_button != null && FireEye.isAddViewFlag) {
            MyApp.getInstance().getWindowManagerInstence().removeView(FireEye.float_button);
            FireEye.isAddViewFlag = false;
        }
        FireEye.float_btn_hidden_flag = true;
    }
    
    private boolean GetAndShowStorageSpace() {
        if (!this.mExternalStorage) {
            return false;
        }
        if (this.mRecordingStatus) {
            this.mStorageSpace = FileStorage.GetAvailableSpace();
            if (this.mStorageSpace < 524288000L) {
                if (this.mStorageSpace < 419430400L) {
                    if (!FileStorage.GetVideoFileSpace(419430400L)) {
                        (this.noSpaceDialog = new AlertDialog.Builder((Context)this).setTitle(2131165197).setMessage(2131165199).create()).show();
                        FireEyeTTS.speakChinese(FireEyeTTS.NOT_ENOUGH_SPACE);
                        this.mHandler.sendEmptyMessageDelayed(13, 2000L);
                        return false;
                    }
                    this.mHandler.sendEmptyMessage(7);
                    this.mHandler.sendEmptyMessageDelayed(5, 2000L);
                }
                else {
                    this.mHandler.sendEmptyMessage(7);
                    this.mHandler.sendEmptyMessageDelayed(5, 2000L);
                }
            }
            else {
                this.mHandler.sendEmptyMessageDelayed(5, 1000L);
            }
        }
        else {
            this.mStorageSpace = FileStorage.GetAvailableSpace();
            if (this.mStorageSpace < 104857600L) {
                (this.noSpaceDialog = new AlertDialog.Builder((Context)this).setTitle(2131165197).setMessage(2131165199).create()).show();
                FireEyeTTS.speakChinese(FireEyeTTS.NOT_ENOUGH_SPACE);
                this.mHandler.sendEmptyMessageDelayed(13, 2000L);
                return false;
            }
        }
        return true;
    }
    
    private void LastLoopRecording() {
        this.ShowRecordingLabels(false);
        final long n = System.currentTimeMillis() - this.mRecordingStartTime;
        if (this.hasBackCamera == 1) {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        else if (this.mConfig.getRecordModeValue() == 0) {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.BackSensorID);
        }
        else {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        this.millisecondToWaterMarkIndex(this.mRecordingStartTime = System.currentTimeMillis());
        if (this.hasBackCamera == 1) {
            this.SetMediaOutputFile(2);
        }
        else {
            this.SetMediaOutputFile(0);
        }
        this.ShowRecordingLabels(false);
        this.fileLockToastFlag = false;
        this.mStartWork.setClickable(false);
        if (MiniFireEye.isOpen) {
            MyApp.getInstance().getMiniFireEye().ShowRecordingLabels(false);
        }
        else if (!FireEye.float_btn_hidden_flag) {
            FireEye.float_btn.setBackgroundResource(2130837585);
        }
    }
    
    private void LastStartRecording() {
        if (this.mRecordingStatus || this.mConfig.getWorkStatus() == 1) {
            return;
        }
        this.mRecordingStatus = true;
        this.ShowStorageStatus();
        if (!this.GetAndShowStorageSpace()) {
            this.mRecordingStatus = false;
            this.mStartWork.setBackgroundResource(2130837584);
            return;
        }
        this.sendBroadcast(new Intent("com.softwinner.fireeye.start.recording"));
        this.fileLockToastFlag = false;
        this.mRecordingStartTime = System.currentTimeMillis();
        if (this.mConfig.mMotionStatus == 1) {
            if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.stopRecord(this.mConfig.BackSensorID);
            }
            else {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
            }
        }
        FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
        this.SetMediaOutputFile(2);
        FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
        this.millisecondToWaterMarkIndex(this.mRecordingStartTime);
        this.SetRecordingRepeatTime();
        this.StartDet = 0;
        this.UpdateRecordingTime();
        this.mStartWork.setClickable(false);
    }
    
    private void LastStopRecording() {
        if (!this.mRecordingStatus) {
            return;
        }
        this.mRecordingStatus = false;
        this.sendBroadcast(new Intent("com.softwinner.fireeye.stop.recording"));
        final long n = System.currentTimeMillis() - this.mRecordingStartTime;
        if (this.hasBackCamera == 1) {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        else if (this.mConfig.getRecordModeValue() == 0) {
            FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
            FireEye.mRecorder.stopRecord(this.mConfig.BackSensorID);
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.BackSensorID);
        }
        else {
            FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        this.StartDet = 0;
        if (this.mConfig.mMotionStatus == 1) {
            this.mHandler.removeMessages(8);
            this.SetMotionDetectStatus(true);
        }
        if (this.hasBackCamera == 1) {
            FileStorage.DelLastVideoFile(this.resolver, 1);
        }
        else if (this.mConfig.getRecordModeValue() == 0) {
            FileStorage.DelLastVideoFile(this.resolver, 0);
        }
        else {
            FileStorage.DelLastVideoFile(this.resolver, 1);
        }
        this.mStartWork.setClickable(true);
    }
    
    private void LoopRecording() {
        this.ShowRecordingLabels(false);
        final long n = System.currentTimeMillis() - this.mRecordingStartTime;
        if (this.hasBackCamera == 1) {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        else if (this.mConfig.getRecordModeValue() == 0) {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.BackSensorID);
        }
        else {
            this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
        }
        this.millisecondToWaterMarkIndex(this.mRecordingStartTime = System.currentTimeMillis());
        if (this.hasBackCamera == 1) {
            this.SetMediaOutputFile(2);
        }
        else {
            this.SetMediaOutputFile(0);
        }
        this.ShowRecordingLabels(true);
        this.fileLockToastFlag = false;
    }
    
    private void MotionDetecting() {
        if (this.mConfig.mMotionStatus == 0 || this.mMenuFlags != 0) {
            return;
        }
        int motionStatus;
        if (this.mConfig.getRecordModeValue() == 0) {
            motionStatus = FireEye.mRecorder.getMotionStatus(this.mConfig.FrontSensorID) + FireEye.mRecorder.getMotionStatus(this.mConfig.BackSensorID);
        }
        else {
            motionStatus = FireEye.mRecorder.getMotionStatus(this.mConfig.FrontSensorID);
        }
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "det_value = " + motionStatus);
        }
        if (motionStatus > 0) {
            if (!this.mRecordingStatus) {
                if (this.mStorageFlag) {
                    FireEyeTTS.speakChinese(FireEyeTTS.MOTION_DETECTING);
                }
                this.StartRecording();
            }
            this.LastDet = 0;
            this.StartDet = 0;
            this.mHandler.sendEmptyMessageDelayed(8, 1000L);
            return;
        }
        ++this.LastDet;
        ++this.StartDet;
        this.mHandler.sendEmptyMessageDelayed(8, 1000L);
    }
    
    private void PowerShutDown() {
        if (this.mRecordingStatus) {
            this.StopRecording();
        }
        this.finish();
        final Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(335544320);
        this.startActivity(intent);
    }
    
    @SuppressWarnings("deprecation")
    private void RecordingMiniShow(final boolean b) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "RecordingMiniShow: " + b);
        }
        if (b) {
            FireEye.mNotificationManager = (NotificationManager)this.getSystemService("notification");
            final PendingIntent activity = PendingIntent.getActivity((Context)this, 0, new Intent((Context)this, (Class<?>)MiniFireEye.class), 0);
            final Notification notification = new Notification();
            notification.flags |= 0x28;
            notification.icon = 2130837599;
            notification.setLatestEventInfo((Context)this, (CharSequence)"FireEye", (CharSequence)"Mini Preview", activity);
            FireEye.mNotificationManager.notify(1001, notification);
        }
        else if (FireEye.mNotificationManager != null) {
            FireEye.mNotificationManager.cancel(1001);
        }
    }
    
    private void SavePicture() {
        if (this.mTakeImageStatus) {
            if (this.mConfig.getRecordModeValue() == 0) {
                this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.FrontSensorID);
                this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.BackSensorID);
            }
            else {
                this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.FrontSensorID);
            }
            this.mStartWork.setBackgroundResource(2130837584);
            this.mTakeImageStatus = false;
        }
    }
    
    private void SetDelayShutdown() {
        if (this.mConfig.getPowerDelay()) {
            this.mHandler.sendEmptyMessageDelayed(10, 10000L);
            this.showDelayShutDownDialog();
            return;
        }
        this.mHandler.removeMessages(10);
    }
    
    private void SetMediaOutputFile(final int n) {
        if (n == 0) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.getString(2131165203));
            if (FireEye.CODE_DEBUG) {
                Log.d(FireEye.TAG, "formatter = " + simpleDateFormat);
            }
            if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.setOutputFile(FileStorage.GenerateVideoFilename(simpleDateFormat.format(this.mRecordingStartTime), this.mRecordingStartTime, null, this.mConfig.FrontSensorID), this.mConfig.FrontSensorID);
                FireEye.mRecorder.setOutputFile(FileStorage.GenerateVideoFilename(simpleDateFormat.format(this.mRecordingStartTime), this.mRecordingStartTime, null, this.mConfig.BackSensorID), this.mConfig.BackSensorID);
                return;
            }
            FireEye.mRecorder.setOutputFile(FileStorage.GenerateVideoFilename(simpleDateFormat.format(this.mRecordingStartTime), this.mRecordingStartTime, null, this.mConfig.FrontSensorID), this.mConfig.FrontSensorID);
        }
        else {
            if (n == 2) {
                final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(this.getString(2131165203));
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "formatter = " + simpleDateFormat2);
                }
                FireEye.mRecorder.setOutputFile(FileStorage.GenerateVideoFilename(simpleDateFormat2.format(this.mRecordingStartTime), this.mRecordingStartTime, null, this.mConfig.FrontSensorID), this.mConfig.FrontSensorID);
                return;
            }
            final SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(this.getString(2131165204));
            if (FireEye.CODE_DEBUG) {
                Log.d(FireEye.TAG, "formatter = " + simpleDateFormat3);
            }
            if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.setOutputFile(FileStorage.GenerateImageFilename(simpleDateFormat3.format(this.mTakePictureTime), this.mRecordingStartTime, null, this.mConfig.FrontSensorID), this.mConfig.FrontSensorID);
                FireEye.mRecorder.setOutputFile(FileStorage.GenerateImageFilename(simpleDateFormat3.format(this.mTakePictureTime), this.mRecordingStartTime, null, this.mConfig.BackSensorID), this.mConfig.BackSensorID);
                return;
            }
            FireEye.mRecorder.setOutputFile(FileStorage.GenerateImageFilename(simpleDateFormat3.format(this.mTakePictureTime), this.mRecordingStartTime, null, this.mConfig.FrontSensorID), this.mConfig.FrontSensorID);
        }
    }
    
    private void SetMotionDetectStatus(final boolean b) {
        if (b) {
            if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.prepareRecord(this.mConfig.BackSensorID);
                FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.startRecord(this.mConfig.BackSensorID);
            }
            else {
                FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
            }
            if (this.openMotionDetect) {
                this.mHandler.sendEmptyMessageDelayed(8, 1000L);
                this.openMotionDetect = false;
                return;
            }
            this.mHandler.sendEmptyMessageDelayed(8, 5000L);
        }
        else {
            this.mHandler.removeMessages(8);
            if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.stopRecord(this.mConfig.BackSensorID);
                return;
            }
            FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
        }
    }
    
    private void SetRecordingRepeatTime() {
        switch (this.mConfig.getRecordTimeValue()) {
            default:
            case 0:
                this.mRecordingRepeatTime = 60000;
            case 1:
                this.mRecordingRepeatTime = 120000;
            case 2:
                this.mRecordingRepeatTime = 300000;
            case 3:
                this.mRecordingRepeatTime = 300000;
        }
    }
    
    private void ShortcutShow(final boolean b) {
        if (b) {
            if (this.mConfig.getRecordModeValue() == 0) {
                if (this.ShortcutMute == null) {
                    this.ShowShortCutMute();
                }
                else {
                    this.ShortcutMute.setVisibility(0);
                    if (this.mConfig.getRecordMute()) {
                        this.ShortcutMute.setImageResource(2130837577);
                    }
                    else {
                        this.ShortcutMute.setImageResource(2130837579);
                    }
                }
                if (this.ShortcutPreview == null) {
                    this.ShowShortCutPreview();
                }
                else {
                    this.ShortcutPreview.setVisibility(0);
                    if (this.mConfig.getSwitchPreview()) {
                        this.ShortcutPreview.setImageResource(2130837573);
                    }
                    else {
                        this.ShortcutPreview.setImageResource(2130837572);
                    }
                }
                if (this.ShortcutPip == null) {
                    this.ShowShortCutPip();
                }
                else {
                    this.ShortcutPip.setVisibility(0);
                    if (this.mConfig.getPIPStatus()) {
                        this.ShortcutPip.setImageResource(2130837568);
                        return;
                    }
                    this.ShortcutPip.setImageResource(2130837567);
                }
            }
            else {
                if (this.ShortcutMute == null) {
                    this.ShowShortCutMute();
                    return;
                }
                this.ShortcutMute.setVisibility(0);
                if (this.mConfig.getRecordMute()) {
                    this.ShortcutMute.setImageResource(2130837577);
                    return;
                }
                this.ShortcutMute.setImageResource(2130837579);
            }
        }
        else {
            if (this.ShortcutMute != null) {
                this.ShortcutMute.setVisibility(4);
            }
            if (this.ShortcutPreview != null) {
                this.ShortcutPreview.setVisibility(4);
            }
            if (this.ShortcutPip != null) {
                this.ShortcutPip.setVisibility(4);
            }
        }
    }
    
    private void ShowCameraSetting() {
        (this.camera_setting_btn = (ImageButton)this.findViewById(2131361836)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "mMenuFlags = " + FireEye.this.mMenuFlags);
                }
                if (!FireEye.this.mRecordingStatus) {
                    if (FireEye.this.mMenuFlags == 0) {
                        if (FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                            FireEye.this.SetMotionDetectStatus(false);
                        }
                        FireEye.this.ShortcutShow(false);
                        FireEye.this.ShowSettingMenu();
                        return;
                    }
                    if (FireEye.this.mMenuFlags != 3) {
                        FireEye.this.CloseSettingMenu();
                        FireEye.this.ShortcutShow(true);
                        return;
                    }
                    FireEye.this.CloseSettingMenu();
                    FireEye.this.ShowSettingMenu();
                    if (FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                        FireEye.this.SetMotionDetectStatus(false);
                    }
                }
            }
        });
    }
    
    private void ShowFileBrowse() {
        (this.mReviewImage = (ImageButton)this.findViewById(2131361820)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "File Browse");
                }
                if (FireEye.this.mMenuFlags != 0) {
                    FireEye.this.CloseSettingMenu();
                    FireEye.this.ShortcutShow(true);
                }
                else if (!FireEye.this.mRecordingStatus && FireEye.CarReversingFlag == -1 && !FireEye.this.mBrowseStatus) {
                    FireEye.this.mBrowseStatus = true;
                    FireEye.this.mBrowseStatusFlag = true;
                    final Intent intent = new Intent("android.intent.action.MAIN");
                    intent.setComponent(new ComponentName("com.daxun.filesyetem.activity", "com.daxun.filesyetem.activity.VideoShowActivity"));
                    if (FireEye.this.mConfig.getWorkStatus() == 0) {
                        intent.putExtra("isSelect", 0);
                    }
                    else {
                        intent.putExtra("isSelect", 2);
                    }
                    FireEye.this.startActivity(intent);
                    FireEye.this.sendBroadcast(new Intent("android.intent.action.HIDE_STATUS_BAR"));
                }
            }
        });
    }
    
    private void ShowMenuSetList(final int mMenuListSetIndex) {
        this.mMenuSet.startAnimation(AnimationUtils.loadAnimation((Context)this, 2130968583));
        this.mMenuSet.setVisibility(8);
        this.mMenuFlags = 2;
        this.mMenuListSetIndex = mMenuListSetIndex;
        this.mHandler.removeMessages(20000);
        if (this.mSubMenuSet == null) {
            (this.mSubMenuSet = this.findViewById(2131361798)).setVisibility(0);
        }
        this.mSubMenuSet.setVisibility(0);
        this.mMenuListSet = (ListView)this.findViewById(2131361812);
        (this.mMenuSetView = (ImageView)this.findViewById(2131361811)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                FireEye.this.mSubMenuSet.setVisibility(8);
                FireEye.this.mMenuFlags = 0;
                FireEye.this.ShowSettingMenu();
            }
        });
        Label_0160: {
            switch (mMenuListSetIndex) {
                case 5:
                    switch (this.mConfig.getRecordModeValue()) {
                        default:
                            break Label_0160;
                        case 0:
                            this.mMenuSetView.setImageResource(2130837565);
                            break Label_0160;
                        case 1:
                            this.mMenuSetView.setImageResource(2130837564);
                            break Label_0160;
                        case 2:
                            this.mMenuSetView.setImageResource(2130837563);
                            break Label_0160;
                    }
                case 0:
                    switch (this.mConfig.getWhiteBalanceValue()) {
                        default:
                            break Label_0160;
                        case 0:
                            this.mMenuSetView.setImageResource(2130837552);
                            break Label_0160;
                        case 1:
                            this.mMenuSetView.setImageResource(2130837556);
                            break Label_0160;
                        case 2:
                            this.mMenuSetView.setImageResource(2130837554);
                            break Label_0160;
                        case 3:
                            this.mMenuSetView.setImageResource(2130837553);
                            break Label_0160;
                        case 4:
                            this.mMenuSetView.setImageResource(2130837555);
                            break Label_0160;
                    }
                case 1:
                    this.mMenuSetView.setImageResource(2130837557);
                    switch (this.mConfig.getExposureValue()) {
                        default:
                            break Label_0160;
                        case 0:
                            this.mMenuSetView.setImageResource(2130837558);
                            break Label_0160;
                        case 1:
                            this.mMenuSetView.setImageResource(2130837559);
                            break Label_0160;
                        case 2:
                            this.mMenuSetView.setImageResource(2130837560);
                            break Label_0160;
                        case 3:
                            this.mMenuSetView.setImageResource(2130837561);
                            break Label_0160;
                        case 4:
                            this.mMenuSetView.setImageResource(2130837562);
                            break Label_0160;
                    }
                case 2:
                    switch (this.mConfig.getColorModeValue()) {
                        default:
                            break Label_0160;
                        case 0:
                            this.mMenuSetView.setImageResource(2130837537);
                            break Label_0160;
                        case 1:
                            this.mMenuSetView.setImageResource(2130837536);
                            break Label_0160;
                        case 2:
                            this.mMenuSetView.setImageResource(2130837540);
                            break Label_0160;
                        case 3:
                            this.mMenuSetView.setImageResource(2130837538);
                            break Label_0160;
                        case 4:
                            this.mMenuSetView.setImageResource(2130837541);
                            break Label_0160;
                        case 5:
                            this.mMenuSetView.setImageResource(2130837539);
                            break Label_0160;
                        case 6:
                            this.mMenuSetView.setImageResource(2130837535);
                            break Label_0160;
                    }
                case 3:
                    switch (this.mConfig.getRecordTimeValue()) {
                        default:
                            break Label_0160;
                        case 0:
                            this.mMenuSetView.setImageResource(2130837544);
                            break Label_0160;
                        case 1:
                            this.mMenuSetView.setImageResource(2130837545);
                            break Label_0160;
                        case 2:
                            this.mMenuSetView.setImageResource(2130837546);
                            break Label_0160;
                        case 3:
                            this.mMenuSetView.setImageResource(2130837542);
                            break Label_0160;
                        case 4:
                            this.mMenuSetView.setImageResource(2130837542);
                            break Label_0160;
                    }
                case 4:
                    if (this.mConfig.getWorkStatus() == 0) {
                        switch (this.mConfig.getVideoQuality()) {
                            default:
                                this.mMenuSetView.setImageResource(2130837550);
                                break Label_0160;
                            case 0:
                                this.mMenuSetView.setImageResource(2130837550);
                                break Label_0160;
                            case 1:
                                this.mMenuSetView.setImageResource(2130837549);
                                break Label_0160;
                            case 2:
                                this.mMenuSetView.setImageResource(2130837548);
                                break Label_0160;
                        }
                    }
                    else {
                        if (this.mConfig.getWorkStatus() != 1) {
                            break;
                        }
                        switch (this.mConfig.getPhotoQuality()) {
                            default:
                                this.mMenuSetView.setImageResource(2130837611);
                                break Label_0160;
                            case 0:
                                this.mMenuSetView.setImageResource(2130837611);
                                break Label_0160;
                            case 1:
                                this.mMenuSetView.setImageResource(2130837612);
                                break Label_0160;
                            case 2:
                                this.mMenuSetView.setImageResource(2130837613);
                                break Label_0160;
                            case 3:
                                this.mMenuSetView.setImageResource(2130837614);
                                break Label_0160;
                        }
                    }
            }
        }
        this.mMenuListSet.setChoiceMode(1);
        this.mMenuListSet.setAdapter((ListAdapter)new ListSetAdapter((Context)this, mMenuListSetIndex, this.mConfig.getWorkStatus()));
    }
    
    private void ShowMenuSwitchList() {
        if (this.mMenuFlags == 0) {
            this.mMenuFlags = 3;
            if (this.mMenuSwitch == null) {
                (this.mMenuSwitch = this.findViewById(2131361796)).setVisibility(0);
            }
            this.ShortcutShow(false);
            this.mHandler.removeMessages(20000);
            this.mMenuListSwitch = (ViewFlipper)this.findViewById(2131361815);
            this.listSwitchItemViewFrist = (ImageView)this.findViewById(2131361813);
            this.listSwitchItemViewSecond = (ImageView)this.findViewById(2131361814);
            this.isListSwitchFristViewSelected = true;
            this.listSwitchItemFrist = this.getLayoutInflater().inflate(2130903048, (ViewGroup)null);
            this.listSwitchItemSecond = this.getLayoutInflater().inflate(2130903049, (ViewGroup)null);
            this.listSwitchItemGridViewFrist = (GridView)this.listSwitchItemFrist.findViewById(2131361817);
            this.listSwitchItemGridViewSecond = (GridView)this.listSwitchItemSecond.findViewById(2131361819);
            this.listItemAdapterFrist = new ListSwitchAdapterFrist((Context)this);
            this.listItemAdapterSecond = new ListSwitchAdapterSecond((Context)this);
            this.listSwitchItemGridViewFrist.setAdapter((ListAdapter)this.listItemAdapterFrist);
            this.listSwitchItemGridViewSecond.setAdapter((ListAdapter)this.listItemAdapterSecond);
            this.listSwitchItemGridViewFrist.setOnItemClickListener((AdapterView.OnItemClickListener)new AdapterView.OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    final ListSwitchAdapterFrist.ViewHolderFrist viewHolderFrist = (ListSwitchAdapterFrist.ViewHolderFrist)view.getTag();
                    viewHolderFrist.viewSth.setChecked(!viewHolderFrist.viewSth.isChecked());
                }
            });
            this.listSwitchItemGridViewFrist.setOnTouchListener(this.listSwitchOnTouchListener);
            this.listSwitchItemGridViewSecond.setOnItemClickListener((AdapterView.OnItemClickListener)new AdapterView.OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    final ListSwitchAdapterSecond.ViewHolderSecond viewHolderSecond = (ListSwitchAdapterSecond.ViewHolderSecond)view.getTag();
                    if (n == 2) {
                        FireEye.this.FormatExtendStorage();
                    }
                    else {
                        if (n == 4) {
                            FireEye.this.showSensorNumberSwitchDialog();
                            return;
                        }
                        viewHolderSecond.viewSth.setChecked(!viewHolderSecond.viewSth.isChecked());
                        if (n == 5 && viewHolderSecond.viewSth.isChecked()) {
                            FireEye.this.showAutoPowerOffTimeSwitchDialog();
                            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_START);
                            FireEye.this.CloseSettingMenu();
                        }
                    }
                }
            });
            this.listSwitchItemGridViewSecond.setOnTouchListener(this.listSwitchOnTouchListener);
            this.mMenuListSwitch.removeAllViews();
            this.mMenuListSwitch.addView(this.listSwitchItemFrist);
            this.mMenuListSwitch.addView(this.listSwitchItemSecond);
        }
    }
    
    private void ShowSettingMenu() {
        if (this.mMenuFlags == 0) {
            this.mMenuFlags = 1;
            this.ShortcutShow(false);
            this.mMenuSet = this.findViewById(2131361797);
            this.setSelectView();
            this.mMenuSet.setVisibility(0);
            this.mMenuSet.startAnimation(AnimationUtils.loadAnimation((Context)this, 2130968582));
            this.mHandler.removeMessages(20000);
        }
    }
    
    private void ShowShortCutMute() {
        this.ShortcutMute = (ImageButton)this.findViewById(2131361840);
        if (this.mConfig.getRecordMute()) {
            this.ShortcutMute.setImageResource(2130837577);
        }
        else {
            this.ShortcutMute.setImageResource(2130837579);
        }
        this.ShortcutMute.setVisibility(0);
        this.ShortcutMute.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                FireEye.this.mHandler.removeMessages(100002);
                FireEye.this.mHandler.sendEmptyMessageDelayed(100002, 5000L);
                if (FireEye.this.mConfig.getRecordMute()) {
                    FireEye.this.mConfig.setRecordMute(false);
                    FireEye.this.ShortcutMute.setImageResource(2130837579);
                    return;
                }
                FireEye.this.mConfig.setRecordMute(true);
                FireEye.this.ShortcutMute.setImageResource(2130837577);
            }
        });
    }
    
    private void ShowShortCutPreview() {
        this.ShortcutPreview = (ImageButton)this.findViewById(2131361841);
        if (this.mConfig.getSwitchPreview()) {
            this.ShortcutPreview.setImageResource(2130837573);
        }
        else {
            this.ShortcutPreview.setImageResource(2130837572);
        }
        this.ShortcutPreview.setVisibility(0);
        this.ShortcutPreview.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                FireEye.this.mHandler.removeMessages(100002);
                FireEye.this.mHandler.sendEmptyMessageDelayed(100002, 5000L);
                if (FireEye.this.mConfig.getSwitchPreview()) {
                    FireEye.this.mConfig.setSwitchPreview(false);
                    FireEye.this.ShortcutPreview.setImageResource(2130837572);
                    return;
                }
                FireEye.this.mConfig.setSwitchPreview(true);
                FireEye.this.ShortcutPreview.setImageResource(2130837573);
            }
        });
    }
    
    private void ShowStartWork() {
        (this.mStartWork = (ImageButton)this.findViewById(2131361822)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "mRecordingStatus = " + FireEye.this.mRecordingStatus);
                }
                if (FireEye.this.mMenuFlags != 0) {
                    FireEye.this.CloseSettingMenu();
                    FireEye.this.ShortcutShow(true);
                }
                else if (FireEye.this.mConfig.getWorkStatus() == 0) {
                    if (!FireEye.this.mRecordingStatus) {
//                        FireEye.this.mCameraSound.playSound(2);
                        if (FireEye.this.mStorageFlag) {
                            FireEyeTTS.speakChinese(FireEyeTTS.START_RECORDING);
                        }
                        else {
                            FireEyeTTS.speakChinese(FireEyeTTS.INSERT_SDCARD_BEFORE_RECORDING);
                            FireEye.this.noDiskDialog = new AlertDialog.Builder((Context)FireEye.this).setTitle(2131165197).setMessage(2131165198).create();
                            FireEye.this.noDiskDialog.show();
                            FireEye.this.mHandler.sendEmptyMessageDelayed(11, 2000L);
                        }
                        FireEye.this.StartRecording();
                        return;
                    }
//                    FireEye.this.mCameraSound.playSound(3);
                    FireEyeTTS.speakChinese(FireEyeTTS.STOP_RECORDING);
                    FireEye.this.LastLoopRecording();
                    FireEye.this.mHandler.removeMessages(20000);
                    FireEye.this.mHandler.sendEmptyMessage(10000);
                    FireEye.this.mHandler.sendEmptyMessageDelayed(20000, 5000L);
                    view.setBackgroundResource(2130837584);
                    FireEye.this.mHandler.sendEmptyMessageDelayed(1001, 500L);
                }
                else if (!FireEye.this.mTakeImageStatus) {
                    FireEye.this.TakePicture();
                }
            }
        });
    }
    
    private void ShowStorageStatus() {
        this.mExternalStorage = FileStorage.CheckExternalStorage((Context)this);
        Log.d(FireEye.TAG, "mExternalStorage = " + this.mExternalStorage);
        if (!this.mExternalStorage) {
            if (this.mConfig.getWorkStatus() == 0 && this.mConfig.mMotionStatus == 1 && this.mStorageFlag) {
                (this.noDiskDialog = new AlertDialog.Builder((Context)this).setTitle(2131165197).setMessage(2131165198).create()).show();
                FireEyeTTS.speakChineseAfterStop(FireEyeTTS.INSERT_SDCARD_BEFORE_RECORDING);
                this.mHandler.sendEmptyMessageDelayed(11, 2000L);
                this.mStorageFlag = false;
            }
            else if ((this.mConfig.getWorkStatus() == 0 && this.mConfig.mMotionStatus == 0 && this.mStorageFlag) || this.mConfig.getWorkStatus() == 1) {
                (this.noDiskDialog = new AlertDialog.Builder((Context)this).setTitle(2131165197).setMessage(2131165198).create()).show();
                FireEyeTTS.speakChineseAfterStop(FireEyeTTS.INSERT_SDCARD_BEFORE_RECORDING);
                this.mHandler.sendEmptyMessageDelayed(11, 2000L);
            }
            return;
        }
        if (this.noDiskDialog != null) {
            this.noDiskDialog.cancel();
            this.noDiskDialog = null;
        }
        this.mStorageFlag = true;
    }
    
    private void ShowSwitchMode() {
        this.mSwitchMode = (ImageButton)this.findViewById(2131361821);
        this.mStopWorkBtn = (ImageButton)this.findViewById(2131361822);
        this.mSwitchMode.setOnTouchListener((View.OnTouchListener)new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        FireEye.this.mTouchStartY_s = (int)((int)motionEvent.getRawY() - FireEye.this.mSwitchMode.getY());
                        if (FireEye.this.mMenuFlags != 0) {
                            FireEye.this.CloseSettingMenu();
                            FireEye.this.ShortcutShow(true);
                            return true;
                        }
                        if (FireEye.this.mRecordingStatus) {
                            return true;
                        }
                        break;
                    case 1:
                        if (FireEye.this.mSwitchMode.getY() > 27.0f) {
                            FireEye.this.mSwitchMode.setY(54.0f);
                            if (FireEye.this.mConfig.getWorkStatus() == 0) {
                                if (FireEye.this.mMenuFlags != 0) {
                                    FireEye.this.CloseSettingMenu();
                                    FireEye.this.ShortcutShow(true);
                                    return true;
                                }
                                if (FireEye.this.mRecordingStatus) {
                                    break;
                                }
                                if (FireEye.this.mConfig.getWorkStatus() == 0) {
                                    FireEye.this.mSwitchMode.setBackgroundResource(2130837590);
                                    if (FireEye.this.mConfig.mMotionStatus == 1) {
                                        FireEye.this.SetMotionDetectStatus(false);
                                    }
                                    FireEyeTTS.speakChinese(FireEyeTTS.WORKS_STATUS_TAKE_PICTURE);
                                    FireEye.this.mConfig.setWorkStatus(1);
                                }
                                else {
                                    FireEye.this.mSwitchMode.setBackgroundResource(2130837591);
                                    FireEyeTTS.speakChinese(FireEyeTTS.WORKS_STATUS_RECORDING);
                                    FireEye.this.mConfig.setWorkStatus(0);
                                    FireEye.this.mStopWorkBtn.setBackgroundResource(2130837584);
                                    if (FireEye.this.mConfig.mMotionStatus == 1) {
                                        FireEye.this.SetMotionDetectStatus(true);
                                    }
                                }
                            }
                        }
                        if (FireEye.this.mSwitchMode.getY() > 27.0f) {
                            break;
                        }
                        FireEye.this.mSwitchMode.setY(0.0f);
                        if (FireEye.this.mConfig.getWorkStatus() != 1) {
                            break;
                        }
                        if (FireEye.this.mMenuFlags != 0) {
                            FireEye.this.CloseSettingMenu();
                            FireEye.this.ShortcutShow(true);
                            return true;
                        }
                        if (FireEye.this.mRecordingStatus) {
                            break;
                        }
                        if (FireEye.this.mConfig.getWorkStatus() == 0) {
                            FireEye.this.mSwitchMode.setBackgroundResource(2130837590);
                            if (FireEye.this.mConfig.mMotionStatus == 1) {
                                FireEye.this.SetMotionDetectStatus(false);
                            }
                            FireEyeTTS.speakChinese(FireEyeTTS.WORKS_STATUS_TAKE_PICTURE);
                            FireEye.this.mConfig.setWorkStatus(1);
                            return true;
                        }
                        FireEye.this.mSwitchMode.setBackgroundResource(2130837591);
                        FireEyeTTS.speakChinese(FireEyeTTS.WORKS_STATUS_RECORDING);
                        FireEye.this.mConfig.setWorkStatus(0);
                        FireEye.this.mStopWorkBtn.setBackgroundResource(2130837584);
                        if (FireEye.this.mConfig.mMotionStatus == 1) {
                            FireEye.this.SetMotionDetectStatus(true);
                            return true;
                        }
                        break;
                    case 2:
                        if (FireEye.this.mMenuFlags != 0) {
                            FireEye.this.CloseSettingMenu();
                            FireEye.this.ShortcutShow(true);
                            return true;
                        }
                        if (FireEye.this.mRecordingStatus) {
                            break;
                        }
                        if ((int)motionEvent.getRawY() - FireEye.this.mTouchStartY_s > 54) {
                            FireEye.this.mSwitchMode.setY(54.0f);
                            return true;
                        }
                        if ((int)motionEvent.getRawY() - FireEye.this.mTouchStartY_s < 0) {
                            FireEye.this.mSwitchMode.setY(0.0f);
                            return true;
                        }
                        FireEye.this.mSwitchMode.setY((float)((int)motionEvent.getRawY() - FireEye.this.mTouchStartY_s));
                        return true;
                }
                return true;
            }
        });
    }
    
    private void ShowUserSetting() {
        (this.camera_switch_btn = (ImageButton)this.findViewById(2131361837)).setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "ShowCameraSwitch");
                }
                if (!FireEye.this.mRecordingStatus) {
                    if (FireEye.this.mMenuFlags == 0) {
                        if (FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                            FireEye.this.SetMotionDetectStatus(false);
                        }
                        FireEye.this.ShortcutShow(false);
                        FireEye.this.ShowMenuSwitchList();
                        return;
                    }
                    if (FireEye.this.mMenuFlags == 3) {
                        FireEye.this.CloseSettingMenu();
                        FireEye.this.ShortcutShow(true);
                        return;
                    }
                    FireEye.this.CloseSettingMenu();
                    FireEye.this.ShowMenuSwitchList();
                    if (FireEye.this.mConfig.mMotionStatus == 1 && FireEye.this.mConfig.getWorkStatus() == 0) {
                        FireEye.this.SetMotionDetectStatus(false);
                    }
                }
            }
        });
    }
    
    @SuppressWarnings("unused")
    private void SmartShot() {
        if (this.mTakeImageStatus) {
            return;
        }
        this.ShowStorageStatus();
        if (!this.GetAndShowStorageSpace()) {
            this.mTakeImageStatus = false;
            return;
        }
        this.mTakeImageStatus = true;
        this.mTakePictureTime = System.currentTimeMillis();
        this.SetMediaOutputFile(1);
        if (this.mConfig.getRecordModeValue() == 0) {
            if (!this.mRecordingStatus) {
                FireEye.mRecorder.setOverlaySrc(1, this.mConfig.FrontSensorID);
                FireEye.mRecorder.setOverlaySrc(1, this.mConfig.BackSensorID);
            }
            this.millisecondToWaterMarkIndex(this.mTakePictureTime);
            FireEye.mRecorder.smartShot(this.mConfig.FrontSensorID);
            FireEye.mRecorder.smartShot(this.mConfig.BackSensorID);
        }
        else {
            if (!this.mRecordingStatus) {
                FireEye.mRecorder.setOverlaySrc(1, this.mConfig.FrontSensorID);
            }
            this.millisecondToWaterMarkIndex(this.mTakePictureTime);
            FireEye.mRecorder.smartShot(this.mConfig.FrontSensorID);
        }
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "STORAGE_IMAGE_SUCCEED");
        }
        if (this.mConfig.getRecordModeValue() == 0) {
            this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.FrontSensorID);
            this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.BackSensorID);
        }
        else {
            this.mCurrentVideoUri = FileStorage.AddImageToMediaStore(this.mContentResolver, 0L, this.mConfig.FrontSensorID);
        }
        this.mTakeImageStatus = false;
    }
    
    private void StartRecording() {
        if (!this.mRecordingStatus && this.mConfig.getWorkStatus() != 1) {
            this.mRecordingStatus = true;
            this.ShowStorageStatus();
            if (!this.GetAndShowStorageSpace()) {
                this.mRecordingStatus = false;
                this.mStartWork.setBackgroundResource(2130837584);
                return;
            }
            this.sendBroadcast(new Intent("com.softwinner.fireeye.start.recording"));
            this.mHandler.sendEmptyMessage(20000);
            this.fileLockToastFlag = false;
            this.mRecordingStartTime = System.currentTimeMillis();
            if (this.mConfig.mMotionStatus == 1) {
                if (this.mConfig.getRecordModeValue() == 0) {
                    FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                    FireEye.mRecorder.stopRecord(this.mConfig.BackSensorID);
                }
                else {
                    FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                }
            }
            if (this.hasBackCamera == 1) {
                FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
                this.SetMediaOutputFile(2);
                FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
            }
            else if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.prepareRecord(this.mConfig.BackSensorID);
                this.SetMediaOutputFile(0);
                FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
                FireEye.mRecorder.startRecord(this.mConfig.BackSensorID);
            }
            else {
                FireEye.mRecorder.prepareRecord(this.mConfig.FrontSensorID);
                this.SetMediaOutputFile(0);
                FireEye.mRecorder.startRecord(this.mConfig.FrontSensorID);
            }
            this.mStartWork.setBackgroundResource(2130837583);
            this.millisecondToWaterMarkIndex(this.mRecordingStartTime);
            this.ShowRecordingLabels(true);
            this.SetRecordingRepeatTime();
            this.StartDet = 0;
            this.UpdateRecordingTime();
            if (MiniFireEye.isOpen) {
                MyApp.getInstance().getMiniFireEye().ShowRecordingLabels(true);
                return;
            }
            if (!FireEye.float_btn_hidden_flag) {
                FireEye.float_btn.setBackgroundResource(2130837584);
            }
        }
    }
    
    private void StopRecording() {
        if (this.mRecordingStatus) {
            this.mRecordingStatus = false;
            this.sendBroadcast(new Intent("com.softwinner.fireeye.stop.recording"));
            this.ShowRecordingLabels(false);
            final long n = System.currentTimeMillis() - this.mRecordingStartTime;
            if (this.hasBackCamera == 1) {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
            }
            else if (this.mConfig.getRecordModeValue() == 0) {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
                FireEye.mRecorder.stopRecord(this.mConfig.BackSensorID);
                this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.BackSensorID);
            }
            else {
                FireEye.mRecorder.stopRecord(this.mConfig.FrontSensorID);
                this.mCurrentVideoUri = FileStorage.AddVideoToMediaStore(this.mContentResolver, n, this.mConfig.FrontSensorID);
            }
            this.StartDet = 0;
            if (this.mConfig.mMotionStatus == 1) {
                this.mHandler.removeMessages(8);
                this.SetMotionDetectStatus(true);
            }
            if (MiniFireEye.isOpen) {
                MyApp.getInstance().getMiniFireEye().ShowRecordingLabels(false);
                return;
            }
            if (!FireEye.float_btn_hidden_flag) {
                FireEye.float_btn.setBackgroundResource(2130837585);
            }
        }
    }
    
    private void TakePicture() {
        if (this.mTakeImageStatus || this.mConfig.getWorkStatus() == 0) {
            return;
        }
//        this.mCameraSound.playSound(0);
        this.ShowStorageStatus();
        if (!this.GetAndShowStorageSpace()) {
            this.mTakeImageStatus = false;
            this.mStartWork.setBackgroundResource(2130837584);
            return;
        }
        this.mTakeImageStatus = true;
        this.mTakePictureTime = System.currentTimeMillis();
        this.SetMediaOutputFile(1);
        if (this.mConfig.getRecordModeValue() == 0) {
            FireEye.mRecorder.setOverlaySrc(1, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setOverlaySrc(1, this.mConfig.BackSensorID);
            this.millisecondToWaterMarkIndex(this.mTakePictureTime);
            FireEye.mRecorder.takePicture(this.shutterCallback, this.jpegCallback, this.mConfig.FrontSensorID);
            FireEye.mRecorder.takePicture(this.shutterCallback, this.jpegCallback, this.mConfig.BackSensorID);
        }
        else {
            FireEye.mRecorder.setOverlaySrc(1, this.mConfig.FrontSensorID);
            this.millisecondToWaterMarkIndex(this.mTakePictureTime);
            FireEye.mRecorder.takePicture(this.shutterCallback, this.jpegCallback, this.mConfig.FrontSensorID);
        }
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "STORAGE_IMAGE_SUCCEED");
        }
        this.mHandler.sendEmptyMessageDelayed(6, 1000L);
    }
    
    private void UpdateRecordingTime() {
        if (this.mRecordingStatus) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.millisecondToWaterMarkIndex(currentTimeMillis);
            final long n = currentTimeMillis - this.mRecordingStartTime;
            if (n < 0L) {
                this.StopRecording();
                this.StartRecording();
                return;
            }
            final String millisecondToTimeString = millisecondToTimeString(n, false);
            if (FireEye.CODE_DEBUG) {
                Log.d(FireEye.TAG, "milli second : " + millisecondToTimeString);
            }
            this.mRecordingTime.setText((CharSequence)millisecondToTimeString);
            if (n >= this.mRecordingRepeatTime) {
                if (this.mConfig.mMotionStatus == 0) {
                    this.LoopRecording();
                    this.mHandler.sendEmptyMessageDelayed(4, 500L);
                    return;
                }
                if (this.LastDet > 5) {
                    this.openMotionDetect = true;
                    this.StopRecording();
                    this.mStartWork.setBackgroundResource(2130837584);
                }
                else {
                    this.LoopRecording();
                    this.mHandler.sendEmptyMessageDelayed(4, 500L);
                }
                this.LastDet = 0;
            }
            else {
                if (this.mFlickerFlags == 0) {
                    this.mFlickerFlags = 1;
                    this.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837575, 0, 0, 0);
                }
                else {
                    this.mFlickerFlags = 0;
                    this.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837576, 0, 0, 0);
                }
                this.mHandler.sendEmptyMessageDelayed(4, 500L);
                if (this.StartDet > 20) {
                    this.openMotionDetect = true;
                    this.StopRecording();
                    this.mStartWork.setBackgroundResource(2130837584);
                    this.StartDet = 0;
                }
            }
        }
    }
    
    private void backPreview(final boolean b) {
        if (!b) {
            FireEye.mRecorder.stopPreview(this.mConfig.BackSensorID);
            FireEye.mRecorder.setPreviewDisplay(null, this.mConfig.BackSensorID);
            this.mBackPreviewing = false;
            return;
        }
        if (this.mConfig.getPIPStatus()) {
            FireEye.mRecorder.stopPreview(this.mConfig.BackSensorID);
            FireEye.mRecorder.setPreviewDisplay(FireEye.bHolder, this.mConfig.BackSensorID);
            FireEye.mRecorder.startPreview(this.mConfig.BackSensorID);
            this.mBackPreviewing = true;
            return;
        }
        FireEye.bSurfaceView.setVisibility(4);
    }
    
    public static boolean fSurfaceViewStatus() {
        return FireEye.fSurfaceView.getVisibility() == 4;
    }
    
    private void frontPreview(final boolean b) {
        if (b) {
            FireEye.mRecorder.stopPreview(this.mConfig.FrontSensorID);
            FireEye.mRecorder.setPreviewDisplay(FireEye.fHolder, this.mConfig.FrontSensorID);
            FireEye.mRecorder.startPreview(this.mConfig.FrontSensorID);
            this.mFrontPreviewing = true;
            return;
        }
        FireEye.mRecorder.stopPreview(this.mConfig.FrontSensorID);
        FireEye.mRecorder.setPreviewDisplay(null, this.mConfig.FrontSensorID);
        this.mFrontPreviewing = false;
    }
    
    private int getCurrentSpeed() {
        if (this.currentLocation != null) {
            this.speedMS = this.currentLocation.getSpeed();
            this.speedKMH = this.speedMS * 3.6;
        }
        return (int)this.speedKMH;
    }
    
    private void initRecordLayout() {
        this.settingLayout = this.findViewById(2131361795);
        this.shortCutLayout = this.findViewById(2131361799);
        this.showShortcutAnimation = AnimationUtils.loadAnimation((Context)this, 2130968597);
        this.mHandler.sendEmptyMessageDelayed(20000, 5000L);
        this.mHandler.sendEmptyMessageDelayed(100002, 5000L);
    }
    
    private void initTTS() {
        if (this.mConfig.getTextToSpeech()) {
            FireEyeTTS.StartUseTTS();
        }
        else {
            FireEyeTTS.StopUseTTS();
        }
        if (FireEyeTTS.mTTS == null) {
            FireEyeTTS.mTTS = new TextToSpeech((Context)this, (TextToSpeech.OnInitListener)this);
        }
    }
    
    private void initWaterMark() {
        FireEye.mRecorder.addWaterMarkSource("/system/etc/watermark/vs_480_water_mark_", 14, 0);
        FireEye.mRecorder.addWaterMarkSource("/system/etc/watermark/vs_720_water_mark_", 14, 1);
        FireEye.mRecorder.addWaterMarkSource("/system/etc/watermark/vs_1080_water_mark_", 14, 2);
    }
    
    private static String millisecondToTimeString(long n, final boolean b) {
        final long n2 = n / 1000L;
        final long n3 = n2 / 60L;
        final long n4 = n3 / 60L;
        final long n5 = n3 - 60L * n4;
        final long n6 = n2 - 60L * n3;
        final StringBuilder sb = new StringBuilder();
        if (n4 > 0L) {
            if (n4 < 10L) {
                sb.append('0');
            }
            sb.append(n4);
            sb.append(':');
        }
        if (n5 < 10L) {
            sb.append('0');
        }
        sb.append(n5);
        sb.append(':');
        if (n6 < 10L) {
            sb.append('0');
        }
        sb.append(n6);
        if (b) {
            sb.append('.');
            n = (n - 1000L * n2) / 10L;
            if (n < 10L) {
                sb.append('0');
            }
            sb.append(n);
        }
        return sb.toString();
    }
    
    private void millisecondToWaterMarkIndex(final long n) {
        if (this.mConfig.getWaterMark() || this.mConfig.getSpeedMark()) {
            if (this.mConfig.getWaterMark() && this.mConfig.getSpeedMark()) {
                final Date time = new Date(n);
                final Calendar instance = Calendar.getInstance();
                instance.setTime(time);
                final int value = instance.get(1);
                final int n2 = instance.get(2) + 1;
                final int value2 = instance.get(5);
                final int value3 = instance.get(11);
                final int value4 = instance.get(12);
                final int value5 = instance.get(13);
                final int currentSpeed = this.getCurrentSpeed();
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "mYear : " + value);
                    Log.d(FireEye.TAG, "mMonth : " + n2);
                    Log.d(FireEye.TAG, "mDate : " + value2);
                    Log.d(FireEye.TAG, "mHour : " + value3);
                    Log.d(FireEye.TAG, "mMinute" + value4);
                    Log.d(FireEye.TAG, "mSecond : " + value5);
                }
                FireEye.mMarkIndex[0] = value / 1000;
                FireEye.mMarkIndex[1] = (value - FireEye.mMarkIndex[0] * 1000) / 100;
                FireEye.mMarkIndex[2] = (value - FireEye.mMarkIndex[0] * 1000 - FireEye.mMarkIndex[1] * 100) / 10;
                FireEye.mMarkIndex[3] = value - FireEye.mMarkIndex[0] * 1000 - FireEye.mMarkIndex[1] * 100 - FireEye.mMarkIndex[2] * 10;
                FireEye.mMarkIndex[4] = 11;
                FireEye.mMarkIndex[5] = n2 / 10;
                FireEye.mMarkIndex[6] = n2 - FireEye.mMarkIndex[5] * 10;
                FireEye.mMarkIndex[7] = 11;
                FireEye.mMarkIndex[8] = value2 / 10;
                FireEye.mMarkIndex[9] = value2 - FireEye.mMarkIndex[8] * 10;
                FireEye.mMarkIndex[10] = 10;
                FireEye.mMarkIndex[11] = value3 / 10;
                FireEye.mMarkIndex[12] = value3 - FireEye.mMarkIndex[11] * 10;
                FireEye.mMarkIndex[13] = 12;
                FireEye.mMarkIndex[14] = value4 / 10;
                FireEye.mMarkIndex[15] = value4 - FireEye.mMarkIndex[14] * 10;
                FireEye.mMarkIndex[16] = 12;
                FireEye.mMarkIndex[17] = value5 / 10;
                FireEye.mMarkIndex[18] = value5 - FireEye.mMarkIndex[17] * 10;
                FireEye.mMarkIndex[19] = 10;
                FireEye.mMarkIndex[20] = 13;
                FireEye.mMarkIndex[21] = 12;
                if (currentSpeed >= 100) {
                    FireEye.mMarkIndex[22] = currentSpeed / 100;
                    FireEye.mMarkIndex[23] = (currentSpeed - FireEye.mMarkIndex[20] * 100) / 10;
                    FireEye.mMarkIndex[24] = currentSpeed - FireEye.mMarkIndex[20] * 100 - FireEye.mMarkIndex[21] * 10;
                    FireEye.mMaxIndex = 25;
                }
                else if (currentSpeed >= 10) {
                    FireEye.mMarkIndex[22] = currentSpeed / 10;
                    FireEye.mMarkIndex[23] = currentSpeed - FireEye.mMarkIndex[20] * 10;
                    FireEye.mMaxIndex = 24;
                }
                else if (currentSpeed >= 0 && currentSpeed < 10) {
                    FireEye.mMarkIndex[22] = currentSpeed;
                    FireEye.mMaxIndex = 23;
                }
                if (this.hasBackCamera == 1) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    return;
                }
                if (this.mConfig.getRecordModeValue() == 0) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.BackSensorID);
                    return;
                }
                FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
            }
            else if (this.mConfig.getSpeedMark()) {
                final int currentSpeed2 = this.getCurrentSpeed();
                FireEye.mMarkIndex[0] = 13;
                FireEye.mMarkIndex[1] = 12;
                if (currentSpeed2 >= 100) {
                    FireEye.mMarkIndex[2] = currentSpeed2 / 100;
                    FireEye.mMarkIndex[3] = (currentSpeed2 - FireEye.mMarkIndex[0] * 100) / 10;
                    FireEye.mMarkIndex[4] = currentSpeed2 - FireEye.mMarkIndex[0] * 100 - FireEye.mMarkIndex[1] * 10;
                    FireEye.mMaxIndex = 5;
                }
                else if (currentSpeed2 >= 10) {
                    FireEye.mMarkIndex[2] = currentSpeed2 / 10;
                    FireEye.mMarkIndex[3] = currentSpeed2 - FireEye.mMarkIndex[0] * 10;
                    FireEye.mMaxIndex = 4;
                }
                else if (currentSpeed2 >= 0 && currentSpeed2 < 10) {
                    FireEye.mMarkIndex[2] = currentSpeed2;
                    FireEye.mMaxIndex = 3;
                }
                if (this.hasBackCamera == 1) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    return;
                }
                if (this.mConfig.getRecordModeValue() == 0) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.BackSensorID);
                    return;
                }
                FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
            }
            else if (this.mConfig.getWaterMark()) {
                final Date time2 = new Date(n);
                final Calendar instance2 = Calendar.getInstance();
                instance2.setTime(time2);
                final int value6 = instance2.get(1);
                final int n3 = instance2.get(2) + 1;
                final int value7 = instance2.get(5);
                final int value8 = instance2.get(11);
                final int value9 = instance2.get(12);
                final int value10 = instance2.get(13);
                if (FireEye.CODE_DEBUG) {
                    Log.d(FireEye.TAG, "mYear : " + value6);
                    Log.d(FireEye.TAG, "mMonth : " + n3);
                    Log.d(FireEye.TAG, "mDate : " + value7);
                    Log.d(FireEye.TAG, "mHour : " + value8);
                    Log.d(FireEye.TAG, "mMinute" + value9);
                    Log.d(FireEye.TAG, "mSecond : " + value10);
                }
                FireEye.mMarkIndex[0] = value6 / 1000;
                FireEye.mMarkIndex[1] = (value6 - FireEye.mMarkIndex[0] * 1000) / 100;
                FireEye.mMarkIndex[2] = (value6 - FireEye.mMarkIndex[0] * 1000 - FireEye.mMarkIndex[1] * 100) / 10;
                FireEye.mMarkIndex[3] = value6 - FireEye.mMarkIndex[0] * 1000 - FireEye.mMarkIndex[1] * 100 - FireEye.mMarkIndex[2] * 10;
                FireEye.mMarkIndex[4] = 11;
                FireEye.mMarkIndex[5] = n3 / 10;
                FireEye.mMarkIndex[6] = n3 - FireEye.mMarkIndex[5] * 10;
                FireEye.mMarkIndex[7] = 11;
                FireEye.mMarkIndex[8] = value7 / 10;
                FireEye.mMarkIndex[9] = value7 - FireEye.mMarkIndex[8] * 10;
                FireEye.mMarkIndex[10] = 10;
                FireEye.mMarkIndex[11] = value8 / 10;
                FireEye.mMarkIndex[12] = value8 - FireEye.mMarkIndex[11] * 10;
                FireEye.mMarkIndex[13] = 12;
                FireEye.mMarkIndex[14] = value9 / 10;
                FireEye.mMarkIndex[15] = value9 - FireEye.mMarkIndex[14] * 10;
                FireEye.mMarkIndex[16] = 12;
                FireEye.mMarkIndex[17] = value10 / 10;
                FireEye.mMarkIndex[18] = value10 - FireEye.mMarkIndex[17] * 10;
                FireEye.mMaxIndex = 19;
                if (this.hasBackCamera == 1) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    return;
                }
                if (this.mConfig.getRecordModeValue() == 0) {
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
                    FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.BackSensorID);
                    return;
                }
                FireEye.mRecorder.refreshWaterMark(16, 16, FireEye.mMaxIndex, FireEye.mMarkIndex, this.mConfig.FrontSensorID);
            }
        }
    }
    
    private void setCarReverse(final boolean mCarReverse) {
        this.mCarReverse = mCarReverse;
        if (this.mCarReverse) {
            this.CarReversing();
        }
    }
    
    private void setListener() {
        this.mScaleGestureListener = (ScaleGestureDetector.OnScaleGestureListener)new ScaleGestureDetector.OnScaleGestureListener() {
            public boolean onScale(final ScaleGestureDetector scaleGestureDetector) {
                final float n = scaleGestureDetector.getScaleFactor() / 3.0f;
                final int n2 = (int)(FireEye.bSurfaceView.getWidth() * n);
                final int n3 = (int)(FireEye.bSurfaceView.getHeight() * n);
                int n4 = n2;
                if (n2 < 320) {
                    n4 = 320;
                }
                int width;
                if ((width = n4) > 600) {
                    width = 600;
                }
                int n5;
                if ((n5 = n3) > 400) {
                    n5 = 400;
                }
                int height;
                if ((height = n5) < 240) {
                    height = 240;
                }
                FireEye.this.params = FireEye.bSurfaceView.getLayoutParams();
                FireEye.this.params.width = width;
                FireEye.this.params.height = height;
                FireEye.bSurfaceView.setLayoutParams(FireEye.this.params);
                FireEye.this.params = null;
                return false;
            }
            
            public boolean onScaleBegin(final ScaleGestureDetector scaleGestureDetector) {
                return true;
            }
            
            public void onScaleEnd(final ScaleGestureDetector scaleGestureDetector) {
            }
        };
        this.mScaleGestureDetector = new ScaleGestureDetector((Context)this, this.mScaleGestureListener);
        FireEye.bSurfaceView.setOnTouchListener((View.OnTouchListener)new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return true;
            }
        });
        this.CarReversingSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 480 - n * 5);
                if (n > 70) {
                    final RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 130);
                    layoutParams2.topMargin = 350;
                    FireEye.this.mConfig.setCarReversingScaleLayoutParams(350);
                    FireEye.this.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
                    return;
                }
                layoutParams.topMargin = n * 5;
                FireEye.this.mConfig.setCarReversingScaleLayoutParams(n * 5);
                FireEye.this.CarReversingScale.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
            }
        });
        this.listSwitchSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(final MotionEvent motionEvent) {
                return false;
            }
            
            public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                final boolean b = false;
                final boolean b2 = false;
                final boolean b3 = false;
                boolean b4 = false;
                if (motionEvent.getRawX() - motionEvent2.getRawX() > 80.0f) {
                    if (!FireEye.this.isListSwitchFristViewSelected) {
                        FireEye.this.listSwitchItemViewFrist.setImageResource(2130837603);
                        FireEye.this.listSwitchItemViewSecond.setImageResource(2130837604);
                        final FireEye this$0 = FireEye.this;
                        if (!FireEye.this.isListSwitchFristViewSelected) {
                            b4 = true;
                        }
                        this$0.isListSwitchFristViewSelected = b4;
                        FireEye.this.mMenuListSwitch.startAnimation(AnimationUtils.loadAnimation((Context)FireEye.this, 2130968580));
                    }
                    else {
                        FireEye.this.listSwitchItemViewFrist.setImageResource(2130837604);
                        FireEye.this.listSwitchItemViewSecond.setImageResource(2130837603);
                        final FireEye this$ = FireEye.this;
                        boolean b5 = b;
                        if (!FireEye.this.isListSwitchFristViewSelected) {
                            b5 = true;
                        }
                        this$.isListSwitchFristViewSelected = b5;
                        FireEye.this.mMenuListSwitch.startAnimation(AnimationUtils.loadAnimation((Context)FireEye.this, 2130968580));
                    }
                    FireEye.this.mMenuListSwitch.showNext();
                }
                else if (motionEvent2.getRawX() - motionEvent.getRawX() > 80.0f) {
                    if (!FireEye.this.isListSwitchFristViewSelected) {
                        FireEye.this.listSwitchItemViewFrist.setImageResource(2130837603);
                        FireEye.this.listSwitchItemViewSecond.setImageResource(2130837604);
                        final FireEye this$2 = FireEye.this;
                        boolean b6 = b2;
                        if (!FireEye.this.isListSwitchFristViewSelected) {
                            b6 = true;
                        }
                        this$2.isListSwitchFristViewSelected = b6;
                        FireEye.this.mMenuListSwitch.startAnimation(AnimationUtils.loadAnimation((Context)FireEye.this, 2130968578));
                    }
                    else {
                        FireEye.this.listSwitchItemViewFrist.setImageResource(2130837604);
                        FireEye.this.listSwitchItemViewSecond.setImageResource(2130837603);
                        final FireEye this$3 = FireEye.this;
                        boolean b7 = b3;
                        if (!FireEye.this.isListSwitchFristViewSelected) {
                            b7 = true;
                        }
                        this$3.isListSwitchFristViewSelected = b7;
                        FireEye.this.mMenuListSwitch.startAnimation(AnimationUtils.loadAnimation((Context)FireEye.this, 2130968578));
                    }
                    FireEye.this.mMenuListSwitch.showPrevious();
                    return true;
                }
                return true;
            }
            
            public boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                return false;
            }
            
            public boolean onSingleTapUp(final MotionEvent motionEvent) {
                return false;
            }
        };
        this.listSwitchDetector = new GestureDetector((Context)this, (GestureDetector.OnGestureListener)this.listSwitchSimpleOnGestureListener);
        this.listSwitchOnTouchListener = (View.OnTouchListener)new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return FireEye.this.listSwitchDetector.onTouchEvent(motionEvent);
            }
        };
    }
    
    private void setSelectView() {
        final ImageButton imageButton = (ImageButton)this.findViewById(2131361828);
        switch (this.mConfig.getRecordTimeValue()) {
            case 0:
                imageButton.setImageResource(2130837544);
                break;
            case 1:
                imageButton.setImageResource(2130837545);
                break;
            case 2:
                imageButton.setImageResource(2130837546);
                break;
            case 3:
                imageButton.setImageResource(2130837542);
                break;
        }
        final ImageButton imageButton2 = (ImageButton)this.findViewById(2131361829);
        if (this.mConfig.getWorkStatus() == 0) {
            switch (this.mConfig.getVideoQuality()) {
                default:
                    imageButton2.setImageResource(2130837550);
                    break;
                case 0:
                    imageButton2.setImageResource(2130837550);
                    break;
                case 1:
                    imageButton2.setImageResource(2130837549);
                    break;
                case 2:
                    imageButton2.setImageResource(2130837548);
                    break;
            }
        }
        else if (this.mConfig.getWorkStatus() == 1) {
            switch (this.mConfig.getPhotoQuality()) {
                default:
                    imageButton2.setImageResource(2130837611);
                    break;
                case 0:
                    imageButton2.setImageResource(2130837611);
                    break;
                case 1:
                    imageButton2.setImageResource(2130837612);
                    break;
                case 2:
                    imageButton2.setImageResource(2130837613);
                    break;
                case 3:
                    imageButton2.setImageResource(2130837614);
                    break;
            }
        }
        final ImageButton imageButton3 = (ImageButton)this.findViewById(2131361830);
        switch (this.mConfig.getRecordModeValue()) {
            case 0:
                imageButton3.setImageResource(2130837565);
                break;
            case 1:
                imageButton3.setImageResource(2130837564);
                break;
            case 2:
                imageButton3.setImageResource(2130837563);
                break;
        }
        final ImageButton imageButton4 = (ImageButton)this.findViewById(2131361825);
        switch (this.mConfig.getWhiteBalanceValue()) {
            case 0:
                imageButton4.setImageResource(2130837552);
                break;
            case 1:
                imageButton4.setImageResource(2130837556);
                break;
            case 2:
                imageButton4.setImageResource(2130837554);
                break;
            case 3:
                imageButton4.setImageResource(2130837553);
                break;
            case 4:
                imageButton4.setImageResource(2130837555);
                break;
        }
        final ImageButton imageButton5 = (ImageButton)this.findViewById(2131361827);
        switch (this.mConfig.getColorModeValue()) {
            case 0:
                imageButton5.setImageResource(2130837537);
                break;
            case 1:
                imageButton5.setImageResource(2130837536);
                break;
            case 2:
                imageButton5.setImageResource(2130837540);
                break;
            case 3:
                imageButton5.setImageResource(2130837538);
                break;
            case 4:
                imageButton5.setImageResource(2130837541);
                break;
            case 5:
                imageButton5.setImageResource(2130837539);
                break;
            case 6:
                imageButton5.setImageResource(2130837535);
                break;
        }
        final ImageButton imageButton6 = (ImageButton)this.findViewById(2131361826);
        switch (this.mConfig.getExposureValue()) {
            default:
            case 0:
                imageButton6.setImageResource(2130837558);
            case 1:
                imageButton6.setImageResource(2130837559);
            case 2:
                imageButton6.setImageResource(2130837560);
            case 3:
                imageButton6.setImageResource(2130837561);
            case 4:
                imageButton6.setImageResource(2130837562);
        }
    }
    
    @SuppressWarnings("unused")
    private void showAutoPowerOffDialog(final String s) {
        final String string = this.getResources().getString(2131165283) + s;
        if (s.equals("10")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_TEN);
        }
        else if (s.equals("9")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_NINE);
        }
        else if (s.equals("8")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_EIGHT);
        }
        else if (s.equals("7")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_SEVEN);
        }
        else if (s.equals("6")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_SIX);
        }
        else if (s.equals("5")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_FIVE);
        }
        else if (s.equals("4")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_FOUR);
        }
        else if (s.equals("3")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_THREE);
        }
        else if (s.equals("2")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_TWO);
        }
        else if (s.equals("1")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_ONE);
        }
        if (this.showAutoPowerOffDialog == null) {
            this.showAutoPowerOffDialog = new AlertDialog.Builder((Context)this).setTitle((CharSequence)string).setIcon(2130837598).setPositiveButton(2131165284, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    FireEye.this.mAutoPowerOffStartTime = System.currentTimeMillis();
                    FireEye.this.showAutoPowerOffDialogCount = 0;
                    dialogInterface.dismiss();
                }
            }).create();
        }
        else {
            this.showAutoPowerOffDialog.setTitle((CharSequence)string);
        }
        this.showAutoPowerOffDialog.show();
    }
    
    private void showAutoPowerOffTimeSwitchDialog() {
        final String string = this.getResources().getString(2131165279);
        String title;
        if (this.mConfig.getAutoPowerOffTime() == 600000L) {
            title = string + this.getResources().getString(2131165280);
        }
        else if (this.mConfig.getAutoPowerOffTime() == 180000L) {
            title = string + this.getResources().getString(2131165281);
        }
        else if (this.mConfig.getAutoPowerOffTime() == 300000L) {
            title = string + this.getResources().getString(2131165282);
        }
        else {
            title = string + this.getResources().getString(2131165282);
        }
        new AlertDialog.Builder((Context)this).setTitle((CharSequence)title).setIcon(2130837598).setPositiveButton(2131165280, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setAutoPowerOffTime(600000L);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131165281, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setAutoPowerOffTime(180000L);
                dialogInterface.dismiss();
            }
        }).setNeutralButton(2131165282, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setAutoPowerOffTime(300000L);
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    
    @SuppressWarnings("unused")
    private void showAutoPowerOffToastText(final String s) {
        final Toast text = Toast.makeText(this.getApplicationContext(), 2131165273, 300);
        final LinearLayout linearLayout = (LinearLayout)text.getView();
        final ImageView imageView = new ImageView((Context)this);
        imageView.setImageResource(2130837528);
        linearLayout.addView((View)imageView, 0);
        text.setGravity(17, 0, 0);
        text.show();
        if (s.equals("10")) {
            FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_TEN);
        }
        else {
            if (s.equals("9")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_NINE);
                return;
            }
            if (s.equals("8")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_EIGHT);
                return;
            }
            if (s.equals("7")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_SEVEN);
                return;
            }
            if (s.equals("6")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_SIX);
                return;
            }
            if (s.equals("5")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_FIVE);
                return;
            }
            if (s.equals("4")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_FOUR);
                return;
            }
            if (s.equals("3")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_THREE);
                return;
            }
            if (s.equals("2")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_TWO);
                return;
            }
            if (s.equals("1")) {
                FireEyeTTS.speakChinese(FireEyeTTS.AUTO_POWER_OFF_ONE);
            }
        }
    }
    
    private void showDelayShutDownDialog() {
        if (!FireEyeTTS.isUsingTTS()) {
            FireEyeTTS.StartUseTTS();
            FireEyeTTS.speakChinese(FireEyeTTS.DELAY_SHUT_DOWN);
            FireEyeTTS.StopUseTTS();
        }
        else {
            FireEyeTTS.speakChinese(FireEyeTTS.DELAY_SHUT_DOWN);
        }
        (this.showDelayShutDownDialog = new AlertDialog.Builder((Context)this).setIcon(2130837598).setTitle(2131165351).setPositiveButton(2131165189, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.showDelayShutDownDialog.dismiss();
            }
        }).setNegativeButton(2131165190, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mHandler.removeMessages(10);
            }
        }).create()).show();
    }
    
    private void showExitAlertDialog() {
        new AlertDialog.Builder((Context)this).setTitle(2131165285).setIcon(2130837598).setPositiveButton(2131165286, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                final Intent launchIntentForPackage = FireEye.this.getPackageManager().getLaunchIntentForPackage("com.daxun.launcher.activity");
                FireEye.this.isFireEyeExit = true;
                FireEye.this.finish();
                if (launchIntentForPackage != null) {
                    FireEye.this.startActivity(launchIntentForPackage);
                }
            }
        }).setNegativeButton(2131165287, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                final Intent launchIntentForPackage = FireEye.this.getPackageManager().getLaunchIntentForPackage("com.daxun.launcher.activity");
                if (launchIntentForPackage != null) {
                    FireEye.this.startActivity(launchIntentForPackage);
                }
                else {
                    final Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setComponent(new ComponentName("com.android.launcher", "com.android.launcher2.Launcher"));
                    FireEye.this.startActivity(intent);
                }
                final Intent intent2 = new Intent();
                intent2.setClass((Context)FireEye.this, (Class<?>)MiniFireEye.class);
                FireEye.this.startActivity(intent2);
                FireEyeTTS.isFloatBtnFirstStart = true;
            }
        }).create().show();
    }
    
    private void showFileLockToastText() {
        if (!this.fileLockToastFlag) {
            final Toast text = Toast.makeText((Context)this, 2131165272, 1);
            final LinearLayout linearLayout = (LinearLayout)text.getView();
            final ImageView imageView = new ImageView((Context)this);
            imageView.setImageResource(2130837528);
            linearLayout.addView((View)imageView, 0);
            linearLayout.setBackgroundColor(this.getResources().getColor(2131034133));
            text.show();
            FireEyeTTS.speakChinese(FireEyeTTS.FILE_LOCK);
            this.fileLockToastFlag = true;
        }
    }
    
    public static void showFloatBtn() {
        if (FireEye.float_btn_hidden_flag && !MiniFireEye.isOpen) {
            FireEye.wm = MyApp.getInstance().getWindowManagerInstence();
            FireEye.wmParams = MyApp.getInstance().getFloatBtnLayoutParams();
            if (FireEyeAction.getRecordingStatus()) {
                FireEye.float_btn.setBackgroundResource(2130837584);
            }
            else {
                FireEye.float_btn.setBackgroundResource(2130837585);
            }
            FireEye.wm.addView(FireEye.float_button, (ViewGroup.LayoutParams)FireEye.wmParams);
            FireEye.isAddViewFlag = true;
            FireEye.float_btn_hidden_flag = false;
            if (FireEyeTTS.isFloatBtnFirstStart) {
                FireEyeTTS.speakChinese(FireEyeTTS.FLOAT_BTN_OPEN);
            }
            FireEyeTTS.isFloatBtnFirstStart = false;
        }
    }
    
    @SuppressWarnings("unused")
    private void showPic() {
        final ImageButton imageButton = (ImageButton)this.findViewById(2131361820);
        Label_0202: {
            if (this.mConfig.getWorkStatus() != 1) {
                break Label_0202;
            }
            if (!false) {}
            final Cursor query = this.resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/PIC%'", (String[])null, "_data");
            if (query != null) {
                if (!query.moveToLast()) {
                    query.close();
                    return;
                }
                if (query.getString(query.getColumnIndex("_id")) != null) {
                    final String string = query.getString(query.getColumnIndex("_data"));
                    if (string != null) {
                        query.close();
                        final BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
                        bitmapFactoryOptions.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(string, bitmapFactoryOptions);
                        bitmapFactoryOptions.inJustDecodeBounds = false;
                        Label_0188: {
                            if (bitmapFactoryOptions.outHeight / 50 <= bitmapFactoryOptions.outWidth / 50) {
                                break Label_0188;
                            }
                            int outHeight = bitmapFactoryOptions.outHeight;
                            while (true) {
                                bitmapFactoryOptions.inSampleSize = outHeight;
                                try {
//                                    new FileInputStream(string);
                                    final Bitmap decodeFile = BitmapFactory.decodeFile(string, bitmapFactoryOptions);
                                    if (decodeFile != null) {
                                        imageButton.setImageBitmap(decodeFile);
                                        return;
                                    }
                                    break;
//                                    outHeight = bitmapFactoryOptions.outWidth / 50;
//                                    continue;
                                }
                                catch (Exception ex) {
                                    return;
                                }
///                                break Label_0202;
                            }
                        }
                    }
                }
            }
            return;
        }
        if (this.mRecordingStatus || this.mConfig.mMotionStatus == 1) {
            return;
        }
        if (!false) {}
        final Cursor query2 = this.resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
        if (query2 == null) {
            return;
        }
        if (!query2.moveToLast()) {
            query2.close();
            return;
        }
        if (query2.getString(query2.getColumnIndex("_id")) == null) {
            return;
        }
        final String string2 = query2.getString(query2.getColumnIndex("_data"));
        if (string2 == null) {
            return;
        }
        query2.close();
        this.retriever.setDataSource(string2);
        final Bitmap frameAtTime = this.retriever.getFrameAtTime(-1L);
        if (frameAtTime != null) {
            imageButton.setImageBitmap(frameAtTime);
        }
    }
    
    private void showSensorNumberSwitchDialog() {
        final String string = this.getResources().getString(2131165278);
        String title;
        if (this.mConfig.getSensorNumber() == 14.3f) {
            title = string + this.getResources().getString(2131165275);
        }
        else if (this.mConfig.getSensorNumber() == 14.6f) {
            title = string + this.getResources().getString(2131165276);
        }
        else {
            title = string;
            if (this.mConfig.getSensorNumber() == 14.94f) {
                title = string + this.getResources().getString(2131165277);
            }
        }
        new AlertDialog.Builder((Context)this).setTitle((CharSequence)title).setIcon(2130837598).setPositiveButton(2131165275, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setSeneorNumber(14.3f);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131165277, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setSeneorNumber(14.94f);
                dialogInterface.dismiss();
            }
        }).setNeutralButton(2131165276, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                FireEye.this.mConfig.setSeneorNumber(14.6f);
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    
    private void startFireEyeTTS() {
        if (this.mConfig.getAppErrorExit()) {
            FireEyeTTS.speakChinese(FireEyeTTS.UN_CAUGHT_EXCEPTION);
            this.mConfig.setAppErrorExit(false);
        }
        else {
            if (FireEye.CarReversingFlag != -1) {
                FireEyeTTS.speakChinese(FireEyeTTS.CAR_REVERSING);
            }
            FireEyeTTS.speakChineseAfterStop(FireEyeTTS.FIRE_EYE_START);
            if (this.mConfig.getMotionDetect()) {
                FireEyeTTS.speakChineseAfterStop(FireEyeTTS.MOTION_DETECT_OPEN);
            }
            if (this.getIntent().getIntExtra("is_first", 1) == 1) {
                this.ShowStorageStatus();
                if (this.mConfig.getAutoRecording() && (this.mConfig.mMotionStatus != 1 || this.mConfig.getWorkStatus() != 0) && this.mExternalStorage) {
                    FireEyeTTS.speakChineseAfterStop(FireEyeTTS.AUTO_RECORDING);
                    this.mHandler.sendEmptyMessage(9);
                }
            }
        }
    }
    
    private void updateLocation(final Location currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public void FormatExtendStorage() {
        FireEyeTTS.speakChinese(FireEyeTTS.FORMAT_SD_CARD);
        this.CloseSettingMenu();
        this.ShortcutShow(true);
        final AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder((Context)this);
        alertDialog$Builder.setMessage(2131165191);
        alertDialog$Builder.setTitle(2131165188);
        alertDialog$Builder.setPositiveButton(2131165189, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
/*
                final StorageVolume storageVolume = ((StorageManager)FireEye.this.getSystemService("storage")).getVolumeList()[1];
                final Intent intent = new Intent("com.android.internal.os.storage.FORMAT_ONLY");
                intent.setComponent(ExternalStorageFormatter.COMPONENT_NAME);
                intent.putExtra("storage_volume", (Parcelable)storageVolume);
                FireEye.this.startService(intent);
*/
                dialogInterface.dismiss();
            }
        });
        alertDialog$Builder.setNegativeButton(2131165190, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        });
        alertDialog$Builder.create().show();
    }
    
    public void SetBrightness(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetBrightness");
        }
        this.ShowMenuSetList(2);
    }
    
    public void SetContrast(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetContrast");
        }
        this.ShowMenuSetList(3);
    }
    
    public void SetExposure(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetExposure");
        }
        this.ShowMenuSetList(4);
    }
    
    public void SetSaturtion(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetSaturtion");
        }
        this.ShowMenuSetList(0);
    }
    
    public void SetSharpness(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetSharpness");
        }
        this.ShowMenuSetList(1);
    }
    
    public void SetWhiteBalance(final View view) {
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "SetWhiteBalance");
        }
        this.ShowMenuSetList(5);
    }
    
    public void ShowRecordingLabels(final boolean b) {
        if (b) {
            this.mRecordingFlag = (TextView)this.findViewById(2131361838);
            this.mRecordingTime = (TextView)this.findViewById(2131361839);
            this.mFlickerFlags = 0;
            this.mRecordingFlag.setCompoundDrawablesWithIntrinsicBounds(2130837576, 0, 0, 0);
            this.mRecordingTime.setText(2131165185);
            this.mRecordingFlag.setVisibility(0);
            this.mRecordingTime.setVisibility(0);
            return;
        }
        this.mRecordingFlag.setVisibility(8);
        this.mRecordingTime.setVisibility(8);
    }
    
    public void ShowShortCutPip() {
        this.ShortcutPip = (ImageButton)this.findViewById(2131361842);
        if (this.mConfig.getPIPStatus()) {
            this.ShortcutPip.setImageResource(2130837568);
        }
        else {
            this.ShortcutPip.setImageResource(2130837567);
        }
        this.ShortcutPip.setVisibility(0);
        this.ShortcutPip.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                FireEye.this.mHandler.removeMessages(100002);
                FireEye.this.mHandler.sendEmptyMessageDelayed(100002, 5000L);
                if (FireEye.this.mConfig.getPIPStatus()) {
                    FireEye.this.mConfig.setPIPStatus(false);
                    FireEye.this.ShortcutPip.setImageResource(2130837567);
                    return;
                }
                FireEye.this.mConfig.setPIPStatus(true);
                FireEye.this.ShortcutPip.setImageResource(2130837568);
            }
        });
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public boolean getRecordingStatus() {
        return this.mRecordingStatus;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == 0) {
            if (n2 == 1) {
                if (this.mConfig.getTextToSpeech()) {
                    FireEyeTTS.StartUseTTS();
                }
                else {
                    FireEyeTTS.StopUseTTS();
                }
                if (FireEyeTTS.mTTS == null) {
                    (FireEyeTTS.mTTS = new TextToSpeech((Context)this, (TextToSpeech.OnInitListener)this)).setLanguage(Locale.CHINESE);
                }
            }
            else {
                final Intent intent2 = new Intent();
                intent2.setAction("android.speech.tts.engine.INSTALL_TTS_DATA");
                this.startActivity(intent2);
            }
        }
        super.onActivityResult(n, n2, intent);
    }
    
    public void onBatteryPlugged(final int n) {
        if (!this.mPowerStatus) {
            this.mPowerStatus = true;
        }
    }
    
    public void onBatteryUnPlugged() {
        if (this.mPowerStatus) {
            this.mPowerStatus = false;
            this.SetDelayShutdown();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "onCreate FireEye Text-To-Speech Version 1.6 08-05");
        }
        this.sendBroadcast(new Intent("com.wisky.full_screen_on"));
        this.requestWindowFeature(1);
        this.getWindow().setFlags(1024, 1024);
        this.setContentView(2130903041);
        this.initRecordLayout();
        MyApp.getInstance().addActivity(this);
        FireEye.mRecorder = FireEyeShare.getCameraRecorder();
//        this.mCameraSound = new CameraSound();
        this.mConfig = FireEyeShare.getFireEyeConfig((Context)this);
        FireEye.mRecorder.initBackCamera(this.mConfig.getInitBack(), 2);
        this.mConfig.setCameraRecorder(FireEye.mRecorder);
        this.mAutoPowerOffRepeatTime = this.mConfig.getAutoPowerOffTime();
        this.mAutoPowerOffStartTime = System.currentTimeMillis();
        this.showAutoPowerOffDialogCount = 0;
        this.mContentResolver = this.getContentResolver();
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "mContentResolver = " + this.mContentResolver);
        }
        FireEye.fSurfaceView = (SurfaceView)this.findViewById(2131361793);
        FireEye.fSurfaceView.getHolder().addCallback(this.frontCallback);
        this.mConfig.setFrontSurfaceView(FireEye.fSurfaceView);
        FireEye.bSurfaceView = (SurfaceView)this.findViewById(2131361792);
        FireEye.bSurfaceView.getHolder().addCallback(this.backCallback);
        this.mConfig.setBackSurfaceView(FireEye.bSurfaceView);
        FireEye.bSurfaceView.setZOrderMediaOverlay(true);
        this.CarReversingScale = (ImageView)this.findViewById(2131361801);
        this.CarReversingSeekBar = (SeekBar)this.findViewById(2131361802);
        this.setListener();
        this.locationManager = (LocationManager)this.getSystemService("location");
        final Location lastKnownLocation = this.locationManager.getLastKnownLocation("gps");
        if (lastKnownLocation != null) {
            this.updateLocation(lastKnownLocation);
        }
        if (this.mConfig.getRecordModeValue() == 0) {
            if (this.mConfig.getSwitchPreview()) {
                FireEye.mRecorder.setVideoQuality(this.mConfig.mBkVideoQuality, this.mConfig.FrontSensorID);
                FireEye.mRecorder.setVideoQuality(this.mConfig.mVideoQuality, this.mConfig.BackSensorID);
            }
            else {
                FireEye.mRecorder.setVideoQuality(this.mConfig.mVideoQuality, this.mConfig.FrontSensorID);
                FireEye.mRecorder.setVideoQuality(this.mConfig.mBkVideoQuality, this.mConfig.BackSensorID);
            }
            FireEye.mRecorder.startCamera(this.mConfig.FrontSensorID);
            FireEye.mRecorder.startCamera(this.mConfig.BackSensorID);
        }
        else {
            if (this.mConfig.getSwitchPreview()) {
                FireEye.mRecorder.setVideoQuality(this.mConfig.mBkVideoQuality, this.mConfig.FrontSensorID);
            }
            else {
                FireEye.mRecorder.setVideoQuality(this.mConfig.mVideoQuality, this.mConfig.FrontSensorID);
            }
            FireEye.mRecorder.startCamera(this.mConfig.FrontSensorID);
        }
        this.mConfig.setWorkStatus(0);
        if (this.mConfig.getRecordMute()) {
            FireEye.mRecorder.setRecordMute(1, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setRecordMute(1, this.mConfig.BackSensorID);
        }
        else {
            FireEye.mRecorder.setRecordMute(0, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setRecordMute(0, this.mConfig.BackSensorID);
        }
        this.resolver = this.getContentResolver();
        this.retriever = new MediaMetadataRetriever();
        this.ShowFileBrowse();
        this.ShowStartWork();
        this.ShowSwitchMode();
        this.ShowUserSetting();
        this.ShowCameraSetting();
        this.ShortcutShow(true);
        this.initWaterMark();
        if (this.mConfig.getWaterMark() || this.mConfig.getSpeedMark()) {
            FireEye.mRecorder.setWaterMark(1, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setWaterMark(1, this.mConfig.BackSensorID);
        }
        if (this.mConfig.getMotionDetect()) {
            FireEye.mRecorder.setMotionParam(1, 10, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setMotionParam(1, 10, this.mConfig.BackSensorID);
        }
        else {
            FireEye.mRecorder.setMotionParam(0, 0, this.mConfig.FrontSensorID);
            FireEye.mRecorder.setMotionParam(0, 0, this.mConfig.BackSensorID);
        }
        FireEyeTTS.init(this.getResources());
        this.initTTS();
        if (this.mConfig.mMotionStatus == 1 && this.mConfig.getWorkStatus() == 0) {
            this.SetMotionDetectStatus(true);
        }
        else if (this.getIntent().getIntExtra("is_first", 1) == 1) {
            if (!this.mConfig.getAppErrorExit()) {
                if (this.mConfig.getAutoRecording()) {}
            }
            else if (this.mConfig.getIsRecordingBeforeError()) {
                this.mHandler.sendEmptyMessage(9);
            }
        }
        else if (this.getIntent().getIntExtra("is_first", 1) == 0) {}
        this.pm = (PowerManager)this.getSystemService("power");
        (this.wl = this.pm.newWakeLock(1, FireEye.TAG)).acquire();
        FireEye.float_button = View.inflate(this.getApplicationContext(), 2130903042, (ViewGroup)null);
        (FireEye.float_btn = (Button)FireEye.float_button.findViewById(2131361803)).setOnTouchListener(this.mTouchListener);
        this.mHandler.sendEmptyMessageDelayed(12, 100L);
        if (this.mConfig.getLedLight()) {
//            Gpio.writeGpio('H', 11, 1);
        }
        else {
//            Gpio.writeGpio('H', 11, 0);
        }
        if (this.getIntent().getIntExtra("is_first", 1) == 0) {
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.daxun.launcher.activity", "com.daxun.launcher.activity.MainActivity"));
            intent.putExtra("is_first", 0);
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        FireEyeAction.SetActionListener(null);
        this.setCarReverse(false);
        if (this.mConfig.getLedLight()) {
//            Gpio.writeGpio('H', 11, 0);
        }
        this.mConfig.setWhiteBalanceValue(0);
        this.mConfig.setColorModeValue(0);
        this.mConfig.setExposureValue(2);
        this.unregisterReceiver(this.receiver);
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "=======onDestroy");
        }
        this.RecordingMiniShow(false);
        if (this.mRecordingStatus) {
            this.mConfig.mMotionStatus = 0;
            this.StopRecording();
        }
        else if (this.mConfig.mMotionStatus == 1 && this.mConfig.getWorkStatus() == 0) {
            this.SetMotionDetectStatus(false);
        }
        if (this.mConfig.getRecordModeValue() == 0) {
            FireEye.mRecorder.stopCamera(this.mConfig.FrontSensorID);
            FireEye.mRecorder.stopCamera(this.mConfig.BackSensorID);
        }
        else {
            FireEye.mRecorder.stopCamera(this.mConfig.FrontSensorID);
        }
        FireEye.mRecorder.finalizeCameraRecorder();
        this.wl.release();
        if (!FireEye.float_btn_hidden_flag) {
            Exit();
        }
        FireEyeShare.release();
        FireEyeTTS.release();
        this.sendBroadcast(new Intent("com.wisky.full_screen_off"));
    }
    
    public void onExtsdMounted() {
        if (this.noDiskDialog != null) {
            this.noDiskDialog.cancel();
            this.noDiskDialog = null;
        }
    }
    
    public void onExtsdUnMounted() {
        if (this.mRecordingStatus) {
            this.StopRecording();
        }
        if (this.mTakeImageStatus) {
            this.SavePicture();
        }
        this.mStartWork.setBackgroundResource(2130837584);
        this.ShowStorageStatus();
    }
    
    public void onInit(final int n) {
        if (n == 0) {
            FireEyeTTS.mTTS.setLanguage(Locale.CHINESE);
            this.startFireEyeTTS();
        }
        else if (n == -1) {
            Toast.makeText((Context)this, (CharSequence)"Error occurred while initializing Text-To-Speech engine", 1).show();
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 115) {
            if (this.mRecordingStatus) {
                FileStorage.SetFileLock(true);
                this.showFileLockToastText();
            }
            return false;
        }
        if (n == 91) {
            if (this.mConfig.getRecordMute()) {
                this.mConfig.setRecordMute(false);
                this.ShortcutMute.setImageResource(2130837579);
            }
            else {
                this.mConfig.setRecordMute(true);
                this.ShortcutMute.setImageResource(2130837577);
            }
        }
        if (n == 4) {
            if (!this.mRecordingStatus) {
                this.showExitAlertDialog();
            }
            else {
                Toast.makeText((Context)this, 2131165288, 1).show();
                FireEyeTTS.speakChinese(FireEyeTTS.IS_RECORDING_STATUS_NOW);
            }
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onMediaScannerFinished() {
    }
    
    public void onMediaScannerStarted() {
        if (this.getIntent().getIntExtra("is_first", 1) == 0 && this.mConfig.getAutoRecording() && this.mConfig.getWorkStatus() == 0) {
            this.ShowStorageStatus();
            if (this.mConfig.getAutoRecording() && (this.mConfig.mMotionStatus != 1 || this.mConfig.getWorkStatus() != 0)) {
                FireEyeTTS.speakChineseAfterStop(FireEyeTTS.AUTO_RECORDING);
                this.AutoRecording();
            }
        }
    }
    
    public void onPause() {
        super.onPause();
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "======onPause");
        }
        if (FireEye.fSurfaceView.getVisibility() == 0) {
            this.frontPreview(false);
            FireEye.fSurfaceView.setVisibility(4);
        }
        if (this.mConfig.getPIPStatus() && FireEye.bSurfaceView.getVisibility() == 0) {
            this.backPreview(false);
            FireEye.bSurfaceView.setVisibility(4);
        }
        this.locationManager.removeUpdates(this.locationListener);
        if (this.mBrowseStatus) {
            if (this.mConfig.mMotionStatus == 1 && this.mConfig.getWorkStatus() == 0) {
                this.SetMotionDetectStatus(false);
            }
        }
        else {
            this.RecordingMiniShow(true);
        }
        this.isSleep = true;
        final ActivityManager.RunningTaskInfo activityManager$RunningTaskInfo = ((ActivityManager)this.getSystemService("activity")).getRunningTasks(1).get(0);
        final String className = activityManager$RunningTaskInfo.topActivity.getClassName();
        activityManager$RunningTaskInfo.topActivity.getPackageName();
        if (!className.equals("com.softwinner.fireeye.main.FireEye") && !className.equals("com.softwinner.fireeye.minishow.MiniFireEye") && !this.isFireEyeExit && !this.mBrowseStatus) {
            showFloatBtn();
        }
    }
    
    public void onPowerConnected() {
        this.mHandler.removeMessages(10);
        if (this.showDelayShutDownDialog != null) {
            this.showDelayShutDownDialog.dismiss();
        }
    }
    
    public void onPowerDisconnected() {
        this.SetDelayShutdown();
    }
    
    @SuppressWarnings("unused")
    public void onResume() {
        super.onResume();
        if (FireEye.CarReversingFlag == -1) {
            this.sendBroadcast(new Intent("android.intent.action.DISPLAY_STATUS_BAR"));
        }
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "onResume");
        }
    Label_0050:
        while (true) {
            if (MiniFireEye.isOpen) {
                IntentFilter intentFilter;
                Criteria criteria;
                String bestProvider;
                Label_0305_Outer:Label_0377_Outer:Label_0419_Outer:
                while (true) {
                    while (true) {
                    Label_0520:
                        while (true) {
                        Label_0497:
                            while (true) {
                            Label_0489:
                                while (true) {
                                    try {
                                        MiniFireEye.Exit();
                                        if (!FireEye.float_btn_hidden_flag) {
                                            Exit();
                                        }
                                        if (FireEye.fSurfaceView.getVisibility() == 4) {
                                            FireEye.fSurfaceView.setVisibility(0);
                                        }
                                        if (this.mConfig.getPIPStatus() && FireEye.bSurfaceView.getVisibility() == 4) {
                                            FireEye.bSurfaceView.setVisibility(0);
                                        }
                                        intentFilter = new IntentFilter();
                                        intentFilter.addAction("com.fireeye.action.home");
                                        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
                                        intentFilter.addAction("com.daxun.dx_file_system_close");
                                        intentFilter.addAction("com.daxun.dx_file_system_open");
                                        this.registerReceiver(this.receiver, intentFilter);
                                        (this.mGsensor = (SensorManager)this.getSystemService("sensor")).registerListener(this.GsensorListener, this.mGsensor.getDefaultSensor(1), 0);
                                        criteria = new Criteria();
                                        criteria.setAccuracy(1);
                                        criteria.setAltitudeRequired(false);
                                        criteria.setBearingRequired(false);
                                        criteria.setCostAllowed(false);
                                        criteria.setPowerRequirement(1);
                                        bestProvider = this.locationManager.getBestProvider(criteria, true);
                                        if (bestProvider != null) {
                                            this.locationManager.requestLocationUpdates(bestProvider, 1000L, 1.0f, this.locationListener);
                                            if (!this.mBrowseStatus) {
                                                break Label_0489;
                                            }
                                            this.mBrowseStatus = false;
                                            this.mCurrentVideoUri = FileStorage.GetLastVideoFile(this.mContentResolver);
                                            this.mCurrentImageUri = FileStorage.GetLastImageFile(this.mContentResolver);
                                            if (this.mConfig.mMotionStatus == 1 && this.mConfig.getWorkStatus() == 0) {
                                                this.SetMotionDetectStatus(true);
                                            }
                                            if (FireEye.CarReversingFlag == 1) {
                                                this.mConfig.setCarRevsersing(true);
                                            }
                                            FireEyeAction.SetActionListener((FireEyeAction.FireEyeActionListener)this);
                                            this.isSleep = false;
                                            FireEyeTTS.isFloatBtnFirstStart = true;
                                            FireEyeTTS.isMiniFireEyeFirstStart = true;
                                            if (!this.mConfig.getSwitchPreview()) {
                                                break Label_0497;
                                            }
                                            if (this.mConfig.getRecordModeValue() == 0) {
                                                FireEye.mRecorder.setWhiteBalance(this.mConfig.getWhiteBalanceValue(), this.mConfig.BackSensorID);
                                            }
                                            if (!this.mConfig.getSwitchPreview()) {
                                                break Label_0520;
                                            }
                                            if (this.mConfig.getRecordModeValue() == 0) {
                                                FireEye.mRecorder.setExposure(this.mConfig.getExposureValue() - 3, this.mConfig.BackSensorID);
                                            }
                                            if (this.mConfig.getSwitchPreview()) {
                                                if (this.mConfig.getRecordModeValue() == 0) {
                                                    FireEye.mRecorder.setChroma(this.mConfig.getColorModeValue(), this.mConfig.BackSensorID);
                                                }
                                                return;
                                            }
                                            break;
                                        }
                                    }
                                    catch (Exception ex) {
                                        ex.printStackTrace();
                                        continue Label_0050;
                                    }
                                    this.locationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.locationListener);
                                }
                                this.RecordingMiniShow(false);
                            }
                            FireEye.mRecorder.setWhiteBalance(this.mConfig.getWhiteBalanceValue(), this.mConfig.FrontSensorID);
                            continue Label_0419_Outer;
                        }
                        FireEye.mRecorder.setExposure(this.mConfig.getExposureValue() - 3, this.mConfig.FrontSensorID);
                        continue;
                    }
                }
//                FireEye.mRecorder.setChroma(this.mConfig.getColorModeValue(), this.mConfig.FrontSensorID);
            }
            continue Label_0050;
        }
    }
    
    protected void onStop() {
        super.onStop();
        if (FireEye.CODE_DEBUG) {
            Log.d(FireEye.TAG, "======onStop");
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.mTouchEndX = (int)motionEvent.getRawX();
            this.mTouchEndY = (int)motionEvent.getRawY();
            this.secClick = System.currentTimeMillis();
            if (this.mConfig.getRecordModeValue() == 0 && this.secClick - this.firClick < 500L && (Math.abs(this.mTouchStartX - this.mTouchEndX) > 100 || Math.abs(this.mTouchStartY - this.mTouchEndY) > 100)) {
                if (this.mConfig.getSwitchPreview()) {
                    this.mConfig.setSwitchPreview(false);
                    if (this.ShortcutPreview != null) {
                        this.ShortcutPreview.setImageResource(2130837572);
                    }
                }
                else {
                    this.mConfig.setSwitchPreview(true);
                    if (this.ShortcutPreview != null) {
                        this.ShortcutPreview.setImageResource(2130837573);
                    }
                }
            }
            if (this.mMenuFlags != 0) {
                this.CloseSettingMenu();
                this.ShortcutShow(true);
            }
            if (this.showSettingLayoutFlag) {
                this.mHandler.removeMessages(20000);
                this.mHandler.sendEmptyMessageDelayed(20000, 5000L);
            }
            if (this.showShortCutFlag) {
                this.mHandler.removeMessages(100002);
                this.mHandler.sendEmptyMessageDelayed(100002, 5000L);
            }
        }
        if (motionEvent.getAction() == 0) {
            this.mTouchStartX = (int)motionEvent.getRawX();
            this.mTouchStartY = (int)motionEvent.getRawY();
            this.firClick = System.currentTimeMillis();
            if (this.showSettingLayoutFlag) {
                this.mHandler.removeMessages(20000);
            }
            else {
                this.mHandler.sendEmptyMessage(10000);
            }
            if (this.showShortCutFlag) {
                this.mHandler.removeMessages(100002);
            }
            else {
                this.mHandler.sendEmptyMessage(100001);
            }
            this.mAutoPowerOffStartTime = System.currentTimeMillis();
            this.showAutoPowerOffDialogCount = 0;
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void uncaughtExceptionStopRecording() {
        this.StopRecording();
    }
    
    private class MainHandler extends Handler
    {
        public void handleMessage(final Message message) {
            switch (message.what) {
                case 100001:
                    FireEye.this.shortCutLayout.startAnimation(FireEye.this.showShortcutAnimation);
                    FireEye.this.showShortCutFlag = true;
                    FireEye.this.shortCutLayout.setVisibility(0);
                    if (FireEye.this.mConfig.getRecordModeValue() == 0) {
                        if (FireEye.this.ShortcutPreview != null) {
                            FireEye.this.ShortcutPreview.setVisibility(0);
                        }
                        if (FireEye.this.ShortcutMute != null) {
                            FireEye.this.ShortcutMute.setVisibility(0);
                        }
                        if (FireEye.this.ShortcutPip != null) {
                            FireEye.this.ShortcutPip.setVisibility(0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (FireEye.this.ShortcutMute != null) {
                            FireEye.this.ShortcutMute.setVisibility(0);
                            return;
                        }
                        break;
                    }
                case 100002:
                    if (!FireEye.this.showShortCutFlag) {
                        break;
                    }
                    FireEye.this.showShortCutFlag = false;
                    FireEye.this.shortCutLayout.setVisibility(8);
                    if (FireEye.this.mConfig.getRecordModeValue() == 0) {
                        if (FireEye.this.ShortcutPreview != null) {
                            FireEye.this.ShortcutPreview.setVisibility(8);
                        }
                        if (FireEye.this.ShortcutMute != null) {
                            FireEye.this.ShortcutMute.setVisibility(8);
                        }
                        if (FireEye.this.ShortcutPip != null) {
                            FireEye.this.ShortcutPip.setVisibility(8);
                            return;
                        }
                        break;
                    }
                    else {
                        if (FireEye.this.ShortcutPip != null) {
                            FireEye.this.ShortcutMute.setVisibility(8);
                            return;
                        }
                        break;
                    }
                case 10000:
                    if (!FireEye.this.mRecordingStatus) {
                        FireEye.this.showSettingLayoutFlag = true;
                        FireEye.this.settingLayout.setVisibility(0);
                        FireEye.this.camera_setting_btn.setVisibility(0);
                        FireEye.this.camera_switch_btn.setVisibility(0);
                        return;
                    }
                    break;
                case 20000:
                    if (FireEye.this.showSettingLayoutFlag) {
                        FireEye.this.showSettingLayoutFlag = false;
                        FireEye.this.settingLayout.setVisibility(8);
                        FireEye.this.camera_setting_btn.setVisibility(8);
                        FireEye.this.camera_switch_btn.setVisibility(8);
                        return;
                    }
                    break;
                case 4:
                    FireEye.this.UpdateRecordingTime();
                case 5:
                    if (!FireEye.this.GetAndShowStorageSpace()) {
                        if (FireEye.this.mRecordingStatus) {
                            FireEye.this.StopRecording();
                        }
                        if (FireEye.this.mTakeImageStatus) {
                            FireEye.this.SavePicture();
                        }
                        FireEye.this.mStartWork.setBackgroundResource(2130837584);
                        return;
                    }
                    break;
                case 6:
                    FireEye.this.SavePicture();
                case 7:
                    FileStorage.DelFirstVideoFile(FireEye.this.mContentResolver);
                case 8:
                    FireEye.this.MotionDetecting();
                case 9:
                    FireEye.this.AutoRecording();
                case 10:
                    FireEye.this.PowerShutDown();
                case 11:
                    if (FireEye.this.noDiskDialog != null) {
                        FireEye.this.noDiskDialog.cancel();
                        FireEye.this.noDiskDialog = null;
                        return;
                    }
                    break;
                case 13:
                    if (FireEye.this.noSpaceDialog != null) {
                        FireEye.this.noSpaceDialog.cancel();
                        FireEye.this.noSpaceDialog = null;
                        return;
                    }
                    break;
                case 100:
                    if (FireEye.this.noDiskDialog != null) {
                        FireEye.this.noDiskDialog.cancel();
                        FireEye.this.noDiskDialog = null;
                        return;
                    }
                    break;
                case 101:
                    if (FireEye.this.mRecordingStatus) {
                        FireEye.this.StopRecording();
                    }
                    if (FireEye.this.mTakeImageStatus) {
                        FireEye.this.SavePicture();
                    }
                    FireEye.this.mStartWork.setBackgroundResource(2130837584);
                case 12:
                    FireEye.this.CarReversing();
                case 1000:
                    FireEye.this.LastStartRecording();
                case 1001:
                    FireEye.this.LastStopRecording();
            }
        }
    }
}
