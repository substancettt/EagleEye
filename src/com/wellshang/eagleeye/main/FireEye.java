package com.wellshang.eagleeye.main;

import com.wellshang.eagleeye.media.CameraRecorder;
import com.wellshang.eagleeye.receivers.*;
import com.wellshang.eagleeye.receivers.FireEyeAction.FireEyeActionListener;
import com.wellshang.eagleeye.storage.FileStorage;

import android.net.*;
import android.media.*;
import android.util.*;
import android.hardware.*;
import android.app.*;

import java.text.*;

import android.view.animation.*;

import com.wellshang.eagleeye.adapter.*;

import android.speech.tts.*;
import android.widget.*;

import java.io.*;

import android.provider.*;
import android.database.*;
import android.graphics.*;
import android.os.storage.*;

import java.util.*;

import com.wellshang.eagleeye.*;

import android.view.*;
import android.content.*;
import android.location.*;
import android.os.*;

public class FireEye extends Activity implements FireEyeActionListener, TextToSpeech.OnInitListener
{
    private static final int AUTO_START_RECORDING = 9;
    private static final int CAR_REVERSING = 12;
    private static final String CLASS_NAME = "com.softwinner.fireeye.main.FireEye";
    private static boolean CODE_DEBUG = false;
    public static int CarReversingFlag = 0;
    private static final String DA_XUN_LAUNCHER = "com.daxun.launcher.activity.MainActivity";
    public static final String DX_FILE_SYSTEM_CLOSE = "com.daxun.dx_file_system_close";
    public static final String DX_FILE_SYSTEM_OPEN = "com.daxun.dx_file_system_open";
    private static final int EXTSD_MOUNTED = 100;
    private static final int EXTSD_UNMOUNTED = 101;
    private static final String FIREEYE_HOME = "com.fireeye.action.home";
    public static final String FIRE_EYE_START_RECORDING = "com.softwinner.fireeye.start.recording";
    public static final String FIRE_EYE_STOP_RECORDING = "com.softwinner.fireeye.stop.recording";
    private static final int HIDEN_SETTING_LAYOUT = 20000;
    private static final int HIDEN_SHORTCUT_LAYOUT = 100002;
    private static final int LAST_START_RECORDING = 1000;
    private static final int LAST_STOP_RECORDING = 1001;
    private static final String MINI_CLASS_NAME = "com.softwinner.fireeye.minishow.MiniFireEye";
    public static final int MINI_STATUS_SHOW_ID = 1001;
    private static final int MY_DATA_CHECK_CODE = 0;
    private static final int NO_EXTSD_SHOW = 11;
    private static final int NO_SPACE_SHOW = 13;
    private static final int POWER_OFF_DELAY = 10;
    private static final int SHOW_SETTING_LAYOUT = 10000;
    private static final int SHOW_SHORTCUT_LAYOUT = 100001;
    private static final int STORAGE_DISK_IS_FULL = 7;
    private static final int STORAGE_IMAGE_SUCCEED = 6;
    private static String TAG;
    private static final int UPDATE_MOTION_DETECT = 8;
    private static final int UPDATE_RECORD_TIME = 4;
    private static final int UPDATE_STORAGE_SPACE = 5;
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
    private int bottom;
    private ImageButton camera_setting_btn;
    private ImageButton camera_switch_btn;
    private String className;
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
    private int lastX;
    private int lastY;
    private int left;
//    private ListSwitchAdapterFrist listItemAdapterFrist;
//    private ListSwitchAdapterSecond listItemAdapterSecond;
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
//    private final Handler mHandler;
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
    private String packageName;
    private ViewGroup.LayoutParams params;
    private PowerManager pm;
    private BroadcastReceiver receiver;
    private ContentResolver resolver;
    private MediaMetadataRetriever retriever;
    private int right;
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
    private int top;
    private int value;
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
    }
    
    
    public boolean getRecordingStatus() {
        return this.mRecordingStatus;
    }
    
    public void onBatteryPlugged(final int n) {
        if (!this.mPowerStatus) {
            this.mPowerStatus = true;
        }
    }
    
    public void onBatteryUnPlugged() {
        if (this.mPowerStatus) {
            this.mPowerStatus = false;
//            this.SetDelayShutdown();
        }
    }
    
    public void onExtsdMounted() {
        if (this.noDiskDialog != null) {
            this.noDiskDialog.cancel();
            this.noDiskDialog = null;
        }
    }
    
    public void onExtsdUnMounted() {
    }
    
    public void onInit(final int n) {
        if (n == 0) {
        }
        else if (n == -1) {
            Toast.makeText((Context)this, (CharSequence)"Error occurred while initializing Text-To-Speech engine", 1).show();
        }
    }
    
    public void onMediaScannerFinished() {
    }
    
    public void onMediaScannerStarted() {
    }
    
    public void onPowerConnected() {
    }
    
    public void onPowerDisconnected() {
    }
    
    public void uncaughtExceptionStopRecording() {
    }
}
