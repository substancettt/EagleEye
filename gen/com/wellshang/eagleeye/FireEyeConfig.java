package com.wellshang.eagleeye;

//import com.softwinner.fireeye.dlna.*;
import com.wellshang.eagleeye.media.*;
import android.content.*;
import android.preference.*;
import android.text.*;
import android.view.*;

public class FireEyeConfig implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static int BACK_CAMERA_ID = 0;
    public static final int CAMERA_NTSC = 1;
    public static final int CAMERA_PAL = 0;
    private static int FRONT_CAMERA_ID = 0;
    public static final float SENSOR_COMMON = 14.6f;
    public static final float SENSOR_HIGHLY = 14.3f;
    public static final float SENSOR_NORMAL = 14.94f;
    private static SurfaceView bSurfaceView;
    private static SurfaceView fSurfaceView;
//    private static DlnaService mDServer;
    private static int mPIPStatus;
    private static CameraRecorder mRecorder;
    private String APP_ERROR_EXIT;
    private String AUTORECORDING;
    private String AUTO_POWER_OFF;
    private String AUTO_POWER_OFF_TIME;
    private String BRIGHTNESS;
    public int BackSensorID;
    private String CAR_REVERSING_SCALE_LAYOUT_PARAMS;
    private String COLORMODE;
    private String DLNASHARE;
    private String EXPOSURE;
    private String FIREEYE_AUTO_START;
    public int FrontSensorID;
    private String INIT_BACKCAMERA;
    private String IS_RECORDING_BEFORE_ERROR;
    private String LED_LIGHT;
    private String LIGHTFREQ;
    private String LOCATIONINFO;
    private String MOTIONDETECT;
    private String PHOTO_QUALITY;
    private String PIP;
    private String POWERDELAY;
    private String RECORDMODE;
    private String RECORDMUTE;
    private String RECORDTIME;
    private String SENSOR_NUMBER;
    private String SPEEDMARK;
    private String SWITCHPREVIEW;
    private String TEXT_TO_SPEECH;
    private String VIDEO_QUALITY;
    private String WATERMARK;
    private String WHITEBALANCE;
    private String WORK_STATUS;
    public int mBkPhotoQuality;
    public int mBkVideoQuality;
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    public int mMotionStatus;
    public int mPhotoQuality;
    private SharedPreferences mPreference;
    public int mSensorMode;
    public int mVideoQuality;
    private int save_mode;
    private boolean save_pip;
    private boolean save_preview;
    
    static {
        FireEyeConfig.FRONT_CAMERA_ID = 0;
        FireEyeConfig.BACK_CAMERA_ID = 2;
        FireEyeConfig.mPIPStatus = 0;
        FireEyeConfig.fSurfaceView = null;
        FireEyeConfig.bSurfaceView = null;
        FireEyeConfig.mRecorder = null;
//        FireEyeConfig.mDServer = null;
    }
    
    public FireEyeConfig(final Context mContext) {
        this.WHITEBALANCE = "whitebalance";
        this.EXPOSURE = "exposure";
        this.BRIGHTNESS = "brightness";
        this.COLORMODE = "colormode";
        this.RECORDTIME = "recordtime";
        this.RECORDMODE = "recordmode";
        this.WATERMARK = "watermark";
        this.RECORDMUTE = "record-mute";
        this.MOTIONDETECT = "motion-detect";
        this.PIP = "pip";
        this.SWITCHPREVIEW = "switch-preview";
        this.AUTORECORDING = "auto-recording";
        this.POWERDELAY = "power-delay";
        this.LOCATIONINFO = "location-info";
        this.LIGHTFREQ = "light-freq";
        this.DLNASHARE = "dlna-share";
        this.VIDEO_QUALITY = "video-quality";
        this.PHOTO_QUALITY = "photo-quality";
        this.WORK_STATUS = "work-status";
        this.TEXT_TO_SPEECH = "text-to-speech";
        this.LED_LIGHT = "led-light";
        this.SPEEDMARK = "speedmark";
        this.SENSOR_NUMBER = "sensor-number";
        this.AUTO_POWER_OFF_TIME = "auto-power-off-time";
        this.AUTO_POWER_OFF = "auto-power-off";
        this.FIREEYE_AUTO_START = "fireeye-auto-start";
        this.APP_ERROR_EXIT = "app-error-exit";
        this.IS_RECORDING_BEFORE_ERROR = "is-recording-before-error";
        this.CAR_REVERSING_SCALE_LAYOUT_PARAMS = "set-car-reversing-scale-layout-params";
        this.INIT_BACKCAMERA = "init-back-camera";
        this.mBkVideoQuality = 3;
        this.mBkPhotoQuality = 4;
        this.mVideoQuality = 0;
        this.mPhotoQuality = 0;
        this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
        this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
        this.mSensorMode = 0;
        this.mMotionStatus = 0;
        this.mContext = mContext;
        (this.mPreference = PreferenceManager.getDefaultSharedPreferences(this.mContext)).registerOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener)this);
        this.mEditor = this.mPreference.edit();
/*
        FireEyeConfig.mDServer = DlnaService.getInstance(this.mContext);
        if (this.mPreference.getBoolean(this.DLNASHARE, false)) {
            FireEyeConfig.mDServer.enableServer(true);
        }
        else {
            FireEyeConfig.mDServer.enableServer(false);
        }
*/
        switch (this.mSensorMode = this.getRecordModeValue()) {
            default:
                if (this.getSwitchPreview()) {
                    this.FrontSensorID = FireEyeConfig.BACK_CAMERA_ID;
                    this.BackSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                    break;
                }
                this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
                break;
            case 1:
                if (this.getSwitchPreview()) {
                    this.FrontSensorID = FireEyeConfig.BACK_CAMERA_ID;
                    this.BackSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                    break;
                }
                this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
                break;
            case 2:
                if (this.getSwitchPreview()) {
                    this.FrontSensorID = FireEyeConfig.BACK_CAMERA_ID;
                    this.BackSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                    break;
                }
                this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
                break;
        }
        switch (this.getVideoQuality()) {
            default:
                this.mVideoQuality = 0;
                break;
            case 0:
                this.mVideoQuality = 0;
                break;
            case 1:
                this.mVideoQuality = 1;
                break;
            case 2:
                this.mVideoQuality = 2;
                break;
        }
        switch (this.getPhotoQuality()) {
            default:
                this.mPhotoQuality = 1;
            case 0:
                this.mPhotoQuality = 1;
            case 1:
                this.mPhotoQuality = 2;
            case 2:
                this.mPhotoQuality = 3;
            case 3:
                this.mPhotoQuality = 4;
        }
    }
    
    public boolean getAppErrorExit() {
        return this.mPreference.getBoolean(this.APP_ERROR_EXIT, false);
    }
    
    public boolean getAutoPowerOff() {
        return this.mPreference.getBoolean(this.AUTO_POWER_OFF, false);
    }
    
    public long getAutoPowerOffTime() {
        return this.mPreference.getLong(this.AUTO_POWER_OFF_TIME, 300000L);
    }
    
    public boolean getAutoRecording() {
        return this.mPreference.getBoolean(this.AUTORECORDING, true);
    }
    
    public int getBrightNessValue() {
        return this.mPreference.getInt(this.BRIGHTNESS, 0);
    }
    
    public int getCarReversingScaleLayoutParams() {
        return this.mPreference.getInt(this.CAR_REVERSING_SCALE_LAYOUT_PARAMS, 0);
    }
    
    public int getColorModeValue() {
        return this.mPreference.getInt(this.COLORMODE, 0);
    }
    
    public boolean getDlnaShare() {
        return this.mPreference.getBoolean(this.DLNASHARE, false);
    }
    
    public int getExposureValue() {
        return this.mPreference.getInt(this.EXPOSURE, 2);
    }
    
    public boolean getFireEyeAutoStart() {
        return this.mPreference.getBoolean(this.FIREEYE_AUTO_START, true);
    }
    
    public int getInitBack() {
        return this.mPreference.getInt(this.INIT_BACKCAMERA, 1);
    }
    
    public boolean getIsRecordingBeforeError() {
        return this.mPreference.getBoolean(this.IS_RECORDING_BEFORE_ERROR, false);
    }
    
    public boolean getLedLight() {
        return this.mPreference.getBoolean(this.LED_LIGHT, false);
    }
    
    public boolean getLightFreq() {
        return this.mPreference.getBoolean(this.LIGHTFREQ, false);
    }
    
    public boolean getLocationInfo() {
        return this.mPreference.getBoolean(this.LOCATIONINFO, false);
    }
    
    public boolean getMotionDetect() {
        final boolean boolean1 = this.mPreference.getBoolean(this.MOTIONDETECT, false);
        if (boolean1) {
            this.mMotionStatus = 1;
            return boolean1;
        }
        this.mMotionStatus = 0;
        return boolean1;
    }
    
    public boolean getPIPStatus() {
        return this.mPreference.getBoolean(this.PIP, false);
    }
    
    public int getPhotoQuality() {
        return this.mPreference.getInt(this.PHOTO_QUALITY, 0);
    }
    
    public boolean getPowerDelay() {
        return this.mPreference.getBoolean(this.POWERDELAY, true);
    }
    
    public int getRecordModeValue() {
        return this.mPreference.getInt(this.RECORDMODE, 1);
    }
    
    public boolean getRecordMute() {
        return this.mPreference.getBoolean(this.RECORDMUTE, false);
    }
    
    public int getRecordTimeValue() {
        return this.mPreference.getInt(this.RECORDTIME, 0);
    }
    
    public float getSensorNumber() {
        return this.mPreference.getFloat(this.SENSOR_NUMBER, 14.94f);
    }
    
    public boolean getSpeedMark() {
        return this.mPreference.getBoolean(this.SPEEDMARK, false);
    }
    
    public boolean getSwitchPreview() {
        return this.mPreference.getBoolean(this.SWITCHPREVIEW, false);
    }
    
    public boolean getTextToSpeech() {
        return this.mPreference.getBoolean(this.TEXT_TO_SPEECH, true);
    }
    
    public int getVideoQuality() {
        return this.mPreference.getInt(this.VIDEO_QUALITY, 2);
    }
    
    public boolean getWaterMark() {
        return this.mPreference.getBoolean(this.WATERMARK, false);
    }
    
    public int getWhiteBalanceValue() {
        return this.mPreference.getInt(this.WHITEBALANCE, 0);
    }
    
    public int getWorkStatus() {
        return this.mPreference.getInt(this.WORK_STATUS, 0);
    }
    
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String s) {
        if (TextUtils.equals((CharSequence)s, (CharSequence)this.WHITEBALANCE)) {
            if (!this.getSwitchPreview()) {
                FireEyeConfig.mRecorder.setWhiteBalance(this.getWhiteBalanceValue(), this.FrontSensorID);
                return;
            }
            if (this.getRecordModeValue() == 0) {
                FireEyeConfig.mRecorder.setWhiteBalance(this.getWhiteBalanceValue(), this.BackSensorID);
            }
        }
        else if (TextUtils.equals((CharSequence)s, (CharSequence)this.EXPOSURE)) {
            if (!this.getSwitchPreview()) {
                FireEyeConfig.mRecorder.setExposure(this.getExposureValue() - 3, this.FrontSensorID);
                return;
            }
            if (this.getRecordModeValue() == 0) {
                FireEyeConfig.mRecorder.setExposure(this.getExposureValue() - 3, this.BackSensorID);
            }
        }
        else if (TextUtils.equals((CharSequence)s, (CharSequence)this.COLORMODE)) {
            if (!this.getSwitchPreview()) {
                FireEyeConfig.mRecorder.setChroma(this.getColorModeValue(), this.FrontSensorID);
                return;
            }
            if (this.getRecordModeValue() == 0) {
                FireEyeConfig.mRecorder.setChroma(this.getColorModeValue(), this.BackSensorID);
            }
        }
        else if (TextUtils.equals((CharSequence)s, (CharSequence)this.RECORDTIME)) {
            if (this.getRecordMute()) {
                FireEyeConfig.mRecorder.setRecordMute(1, this.FrontSensorID);
                FireEyeConfig.mRecorder.setRecordMute(1, this.BackSensorID);
                return;
            }
            FireEyeConfig.mRecorder.setRecordMute(0, this.FrontSensorID);
            FireEyeConfig.mRecorder.setRecordMute(0, this.BackSensorID);
        }
        else {
            if (TextUtils.equals((CharSequence)s, (CharSequence)this.RECORDMODE)) {
                final int recordModeValue = this.getRecordModeValue();
                if (this.mSensorMode == 0) {
                    if (recordModeValue == 1) {
                        if (this.getPIPStatus()) {
                            FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                            FireEyeConfig.mRecorder.setPreviewDisplay(null, this.BackSensorID);
                            FireEyeConfig.bSurfaceView.setVisibility(4);
                            FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                            FireEyeConfig.mPIPStatus = 1;
                            this.setPIPStatus(false);
                        }
                        else {
                            FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                        }
                        if (this.getSwitchPreview()) {
                            this.setSwitchPreview(false);
                        }
                    }
                    else if (recordModeValue == 2) {
                        if (this.getPIPStatus()) {
                            FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                            FireEyeConfig.mRecorder.setPreviewDisplay(null, this.BackSensorID);
                            FireEyeConfig.bSurfaceView.setVisibility(4);
                            FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                            FireEyeConfig.mPIPStatus = 1;
                            this.setPIPStatus(false);
                        }
                        else {
                            FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                        }
                        if (!this.getSwitchPreview()) {
                            this.setSwitchPreview(true);
                        }
                    }
                }
                else if (this.mSensorMode == 1) {
                    if (recordModeValue == 2) {
                        this.setSwitchPreview(true);
                    }
                    else if (recordModeValue == 0) {
                        if (FireEyeConfig.mPIPStatus == 1) {
                            this.setPIPStatus(true);
                        }
                        else {
                            FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.BackSensorID);
                            FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                        }
                    }
                }
                else if (this.mSensorMode == 2) {
                    if (recordModeValue == 1) {
                        this.setSwitchPreview(false);
                    }
                    else if (recordModeValue == 0) {
                        if (FireEyeConfig.mPIPStatus == 1) {
                            this.setPIPStatus(true);
                        }
                        else {
                            FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                            FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                        }
                    }
                }
                this.mSensorMode = recordModeValue;
                return;
            }
            if (TextUtils.equals((CharSequence)s, (CharSequence)this.WATERMARK)) {
                if (this.getWaterMark()) {
                    FireEyeTTS.speakChinese(FireEyeTTS.TIME_WATER_MARK_OPEN);
                    if (!this.getSpeedMark()) {
                        FireEyeConfig.mRecorder.setWaterMark(1, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setWaterMark(1, this.BackSensorID);
                    }
                }
                else {
                    FireEyeTTS.speakChinese(FireEyeTTS.TIME_WATER_MARK_CLOSE);
                    if (!this.getSpeedMark()) {
                        FireEyeConfig.mRecorder.setWaterMark(0, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setWaterMark(0, this.BackSensorID);
                    }
                }
            }
            else if (TextUtils.equals((CharSequence)s, (CharSequence)this.SPEEDMARK)) {
                if (this.getSpeedMark()) {
                    FireEyeTTS.speakChinese(FireEyeTTS.SPEED_WATER_MARK_OPEN);
                    if (!this.getWaterMark()) {
                        FireEyeConfig.mRecorder.setWaterMark(1, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setWaterMark(1, this.BackSensorID);
                    }
                }
                else {
                    FireEyeTTS.speakChinese(FireEyeTTS.SPEED_WATER_MARK_CLOSE);
                    if (!this.getWaterMark()) {
                        FireEyeConfig.mRecorder.setWaterMark(0, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setWaterMark(0, this.BackSensorID);
                    }
                }
            }
            else if (!TextUtils.equals((CharSequence)s, (CharSequence)this.SENSOR_NUMBER) && !TextUtils.equals((CharSequence)s, (CharSequence)this.AUTO_POWER_OFF_TIME)) {
                if (TextUtils.equals((CharSequence)s, (CharSequence)this.AUTO_POWER_OFF)) {
                    if (this.getAutoPowerOff()) {
                        this.setMotionDetect(true);
                    }
                }
                else if (!TextUtils.equals((CharSequence)s, (CharSequence)this.FIREEYE_AUTO_START)) {
                    if (TextUtils.equals((CharSequence)s, (CharSequence)this.TEXT_TO_SPEECH)) {
                        if (this.getTextToSpeech()) {
                            FireEyeTTS.StartUseTTS();
                            return;
                        }
                        FireEyeTTS.StopUseTTS();
                    }
                    else if (TextUtils.equals((CharSequence)s, (CharSequence)this.RECORDMUTE)) {
                        if (this.getRecordMute()) {
                            FireEyeTTS.speakChinese(FireEyeTTS.RECORD_MUTE_OPEN);
                            FireEyeConfig.mRecorder.setRecordMute(1, this.FrontSensorID);
                            FireEyeConfig.mRecorder.setRecordMute(1, this.BackSensorID);
                            return;
                        }
                        FireEyeTTS.speakChinese(FireEyeTTS.RECORD_MUTE_CLOSE);
                        FireEyeConfig.mRecorder.setRecordMute(0, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setRecordMute(0, this.BackSensorID);
                    }
                    else if (TextUtils.equals((CharSequence)s, (CharSequence)this.MOTIONDETECT)) {
                        this.getMotionDetect();
                        if (this.mMotionStatus == 1) {
                            FireEyeTTS.speakChinese(FireEyeTTS.MOTION_DETECT_OPEN);
                            FireEyeConfig.mRecorder.setMotionParam(1, 10, this.FrontSensorID);
                            FireEyeConfig.mRecorder.setMotionParam(1, 10, this.BackSensorID);
                            return;
                        }
                        FireEyeTTS.speakChinese(FireEyeTTS.MOTION_DETECT_CLOSE);
                        FireEyeConfig.mRecorder.setMotionParam(0, 0, this.FrontSensorID);
                        FireEyeConfig.mRecorder.setMotionParam(0, 0, this.BackSensorID);
                    }
                    else if (TextUtils.equals((CharSequence)s, (CharSequence)this.PIP)) {
                        if (this.getPIPStatus()) {
                            if (FireEyeConfig.mPIPStatus == 1) {
                                if (this.getSwitchPreview()) {
                                    FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                                }
                                else {
                                    FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.BackSensorID);
                                }
                                FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                                FireEyeConfig.mPIPStatus = 0;
                            }
                            FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.bSurfaceView.getHolder(), this.BackSensorID);
                            FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
                            FireEyeConfig.bSurfaceView.setVisibility(0);
                            return;
                        }
                        if (FireEyeConfig.mPIPStatus != 1) {
                            FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                            FireEyeConfig.mRecorder.setPreviewDisplay(null, this.BackSensorID);
                            FireEyeConfig.bSurfaceView.setVisibility(4);
                        }
                    }
                    else if (TextUtils.equals((CharSequence)s, (CharSequence)this.SWITCHPREVIEW)) {
                        if (this.getRecordModeValue() == 0) {
                            if (this.getPIPStatus()) {
                                FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                                FireEyeConfig.mRecorder.setPreviewDisplay(null, this.BackSensorID);
                            }
                            FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                            FireEyeConfig.mRecorder.setPreviewDisplay(null, this.FrontSensorID);
                            if (this.getSwitchPreview()) {
                                this.FrontSensorID = FireEyeConfig.BACK_CAMERA_ID;
                                this.BackSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                            }
                            else {
                                this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                                this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
                            }
                            if (this.getPIPStatus()) {
                                FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.bSurfaceView.getHolder(), this.BackSensorID);
                                FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
                            }
                            FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.fSurfaceView.getHolder(), this.FrontSensorID);
                            FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                            return;
                        }
                        FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                        FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                        FireEyeConfig.mRecorder.setPreviewDisplay(null, this.FrontSensorID);
                        if (this.getSwitchPreview()) {
                            this.FrontSensorID = FireEyeConfig.BACK_CAMERA_ID;
                            this.BackSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                            FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.FrontSensorID);
                        }
                        else {
                            this.FrontSensorID = FireEyeConfig.FRONT_CAMERA_ID;
                            this.BackSensorID = FireEyeConfig.BACK_CAMERA_ID;
                            FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.FrontSensorID);
                        }
                        FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.fSurfaceView.getHolder(), this.FrontSensorID);
                        FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                        FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                    }
                    else if (!TextUtils.equals((CharSequence)s, (CharSequence)this.POWERDELAY) && !TextUtils.equals((CharSequence)s, (CharSequence)this.LOCATIONINFO)) {
                        if (TextUtils.equals((CharSequence)s, (CharSequence)this.LIGHTFREQ)) {
                            if (this.getSwitchPreview()) {
                                if (this.getRecordModeValue() == 0) {
                                    if (this.getLightFreq()) {
                                        FireEyeConfig.mRecorder.setLineFrequency(0, this.BackSensorID);
                                        return;
                                    }
                                    FireEyeConfig.mRecorder.setLineFrequency(1, this.BackSensorID);
                                }
                            }
                            else {
                                if (this.getLightFreq()) {
                                    FireEyeConfig.mRecorder.setLineFrequency(0, this.FrontSensorID);
                                    return;
                                }
                                FireEyeConfig.mRecorder.setLineFrequency(1, this.FrontSensorID);
                            }
                        }
                        else if (TextUtils.equals((CharSequence)s, (CharSequence)this.VIDEO_QUALITY)) {
                            switch (this.mPreference.getInt(this.VIDEO_QUALITY, 0)) {
                                default:
                                    this.mVideoQuality = 0;
                                    break;
                                case 0:
                                    this.mVideoQuality = 0;
                                    break;
                                case 1:
                                    this.mVideoQuality = 1;
                                    break;
                                case 2:
                                    this.mVideoQuality = 2;
                                    break;
                            }
                            if (!this.getSwitchPreview()) {
                                FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                                FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                                FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.FrontSensorID);
                                FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                                FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                                return;
                            }
                            if (this.getRecordModeValue() == 0) {
                                if (this.getPIPStatus()) {
                                    FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                                    FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                                    FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                                    FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                                    FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
                                    return;
                                }
                                FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                                FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                                FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                            }
                        }
                        else if (TextUtils.equals((CharSequence)s, (CharSequence)this.PHOTO_QUALITY)) {
                            switch (this.mPreference.getInt(this.PHOTO_QUALITY, 0)) {
                                default:
                                    this.mPhotoQuality = 1;
                                    break;
                                case 0:
                                    this.mPhotoQuality = 1;
                                    break;
                                case 1:
                                    this.mPhotoQuality = 2;
                                    break;
                                case 2:
                                    this.mPhotoQuality = 3;
                                    break;
                                case 3:
                                    this.mPhotoQuality = 4;
                                    break;
                            }
                            if (!this.getSwitchPreview()) {
                                FireEyeConfig.mRecorder.setPhotoQuality(this.mPhotoQuality, this.FrontSensorID);
                                FireEyeConfig.mRecorder.setPhotoQuality(this.mBkPhotoQuality, this.BackSensorID);
                                return;
                            }
                            if (this.getRecordModeValue() == 0) {
                                FireEyeConfig.mRecorder.setPhotoQuality(this.mBkPhotoQuality, this.FrontSensorID);
                                FireEyeConfig.mRecorder.setPhotoQuality(this.mPhotoQuality, this.BackSensorID);
                            }
                        }
                        else {
                            if (TextUtils.equals((CharSequence)s, (CharSequence)this.WORK_STATUS)) {
                                this.setWhiteBalanceValue(0);
                                this.setColorModeValue(0);
                                this.setExposureValue(2);
                                if (this.getWorkStatus() == 0) {
                                    if (this.getRecordModeValue() != 0) {
                                        FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                                        FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                                        if (this.getSwitchPreview()) {
                                            FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.FrontSensorID);
                                        }
                                        else {
                                            FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.FrontSensorID);
                                        }
                                        FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                                        FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                                        return;
                                    }
                                    if (this.getPIPStatus()) {
                                        FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                                    }
                                    FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                                    if (this.getSwitchPreview()) {
                                        FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.FrontSensorID);
                                        FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                                    }
                                    else {
                                        FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.FrontSensorID);
                                        FireEyeConfig.mRecorder.setVideoQuality(this.mBkVideoQuality, this.BackSensorID);
                                    }
                                    FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                                    FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                                    if (this.getPIPStatus()) {
                                        FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
                                    }
                                }
                                else {
                                    if (this.getRecordModeValue() != 0) {
                                        FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                                        FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                                        if (this.getSwitchPreview()) {
                                            FireEyeConfig.mRecorder.setPhotoQuality(this.mBkPhotoQuality, this.FrontSensorID);
                                        }
                                        else {
                                            FireEyeConfig.mRecorder.setPhotoQuality(this.mPhotoQuality, this.FrontSensorID);
                                        }
                                        FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                                        FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                                        return;
                                    }
                                    if (this.getPIPStatus()) {
                                        FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
                                    }
                                    FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.stopCamera(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
                                    if (this.getSwitchPreview()) {
                                        FireEyeConfig.mRecorder.setPhotoQuality(this.mBkPhotoQuality, this.FrontSensorID);
                                        FireEyeConfig.mRecorder.setPhotoQuality(this.mPhotoQuality, this.BackSensorID);
                                    }
                                    else {
                                        FireEyeConfig.mRecorder.setPhotoQuality(this.mPhotoQuality, this.FrontSensorID);
                                        FireEyeConfig.mRecorder.setPhotoQuality(this.mBkPhotoQuality, this.BackSensorID);
                                    }
                                    FireEyeConfig.mRecorder.startCamera(this.FrontSensorID);
                                    FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                                    FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
                                    if (this.getPIPStatus()) {
                                        FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setAppErrorExit(final boolean b) {
        this.mEditor.putBoolean(this.APP_ERROR_EXIT, b);
        this.mEditor.commit();
    }
    
    public void setAutoPowerOff(final boolean b) {
        this.mEditor.putBoolean(this.AUTO_POWER_OFF, b);
        this.mEditor.commit();
    }
    
    public void setAutoPowerOffTime(final long n) {
        this.mEditor.putLong(this.AUTO_POWER_OFF_TIME, n);
        this.mEditor.commit();
    }
    
    public void setAutoRecording(final boolean b) {
        this.mEditor.putBoolean(this.AUTORECORDING, b);
        this.mEditor.commit();
    }
    
    public void setBackSurfaceView(final SurfaceView bSurfaceView) {
        FireEyeConfig.bSurfaceView = bSurfaceView;
    }
    
    public void setBrightNessValue(final int n) {
        this.mEditor.putInt(this.BRIGHTNESS, n);
        this.mEditor.commit();
    }
    
    public void setCameraRecorder(final CameraRecorder mRecorder) {
        FireEyeConfig.mRecorder = mRecorder;
    }
    
    public void setCarReversingScaleLayoutParams(final int n) {
        this.mEditor.putInt(this.CAR_REVERSING_SCALE_LAYOUT_PARAMS, n);
        this.mEditor.commit();
    }
    
    public void setCarRevsersing(final boolean b) {
        if (b) {
            this.save_mode = this.getRecordModeValue();
            this.save_pip = this.getPIPStatus();
            this.save_preview = this.getSwitchPreview();
            if (this.save_mode == 0) {
                if (this.save_pip) {
                    if (!this.save_preview) {
                        this.setSwitchPreview(true);
                    }
                    this.setPIPStatus(false);
                }
                else if (!this.save_preview) {
                    this.setSwitchPreview(true);
                }
            }
            else if (this.save_mode == 1) {
                FireEyeConfig.mRecorder.stopPreview(this.FrontSensorID);
                FireEyeConfig.mRecorder.setPreviewDisplay(null, this.FrontSensorID);
                FireEyeConfig.mRecorder.setVideoQuality(this.mVideoQuality, this.BackSensorID);
                FireEyeConfig.mRecorder.startCamera(this.BackSensorID);
                FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.fSurfaceView.getHolder(), this.BackSensorID);
                FireEyeConfig.mRecorder.startPreview(this.BackSensorID);
            }
        }
        else if (this.save_mode == 0) {
            if (this.save_pip) {
                if (!this.save_preview) {
                    this.setSwitchPreview(false);
                }
                this.setPIPStatus(true);
                return;
            }
            if (!this.save_preview) {
                this.setSwitchPreview(false);
            }
        }
        else if (this.save_mode == 1) {
            FireEyeConfig.mRecorder.stopPreview(this.BackSensorID);
            FireEyeConfig.mRecorder.setPreviewDisplay(null, this.BackSensorID);
            FireEyeConfig.mRecorder.stopCamera(this.BackSensorID);
            FireEyeConfig.mRecorder.setPreviewDisplay(FireEyeConfig.fSurfaceView.getHolder(), this.FrontSensorID);
            FireEyeConfig.mRecorder.startPreview(this.FrontSensorID);
        }
    }
    
    public void setColorModeValue(final int n) {
        this.mEditor.putInt(this.COLORMODE, n);
        this.mEditor.commit();
    }
    
    public void setDlnaShare(final boolean b) {
        this.mEditor.putBoolean(this.DLNASHARE, b);
        this.mEditor.commit();
    }
    
    public void setExposureValue(final int n) {
        this.mEditor.putInt(this.EXPOSURE, n);
        this.mEditor.commit();
    }
    
    public void setFireEyeAutoStart(final boolean b) {
        this.mEditor.putBoolean(this.FIREEYE_AUTO_START, b);
        this.mEditor.commit();
    }
    
    public void setFrontSurfaceView(final SurfaceView fSurfaceView) {
        FireEyeConfig.fSurfaceView = fSurfaceView;
    }
    
    public void setInitBack(final int n) {
        this.mEditor.putInt(this.INIT_BACKCAMERA, n);
        this.mEditor.commit();
    }
    
    public void setIsRecordingBeforeError(final boolean b) {
        this.mEditor.putBoolean(this.IS_RECORDING_BEFORE_ERROR, b);
        this.mEditor.commit();
    }
    
    public void setLedLight(final boolean b) {
        this.mEditor.putBoolean(this.LED_LIGHT, b);
        this.mEditor.commit();
    }
    
    public void setLightFreq(final boolean b) {
        this.mEditor.putBoolean(this.LIGHTFREQ, b);
        this.mEditor.commit();
    }
    
    public void setLocationInfo(final boolean b) {
        this.mEditor.putBoolean(this.LOCATIONINFO, b);
        this.mEditor.commit();
    }
    
    public void setMotionDetect(final boolean b) {
        this.mEditor.putBoolean(this.MOTIONDETECT, b);
        this.mEditor.commit();
    }
    
    public void setPIPStatus(final boolean b) {
        this.mEditor.putBoolean(this.PIP, b);
        this.mEditor.commit();
    }
    
    public void setPhotoQuality(final int n) {
        this.mEditor.putInt(this.PHOTO_QUALITY, n);
        this.mEditor.commit();
    }
    
    public void setPowerDelay(final boolean b) {
        this.mEditor.putBoolean(this.POWERDELAY, b);
        this.mEditor.commit();
    }
    
    public void setRecordModeValue(final int n) {
        this.mEditor.putInt(this.RECORDMODE, n);
        this.mEditor.commit();
    }
    
    public void setRecordMute(final boolean b) {
        this.mEditor.putBoolean(this.RECORDMUTE, b);
        this.mEditor.commit();
    }
    
    public void setRecordTimeValue(final int n) {
        this.mEditor.putInt(this.RECORDTIME, n);
        this.mEditor.commit();
    }
    
    public void setSeneorNumber(final float n) {
        this.mEditor.putFloat(this.SENSOR_NUMBER, n);
        this.mEditor.commit();
    }
    
    public void setSpeedMark(final boolean b) {
        this.mEditor.putBoolean(this.SPEEDMARK, b);
        this.mEditor.commit();
    }
    
    public void setSwitchPreview(final boolean b) {
        this.mEditor.putBoolean(this.SWITCHPREVIEW, b);
        this.mEditor.commit();
    }
    
    public void setTextToSpeech(final boolean b) {
        this.mEditor.putBoolean(this.TEXT_TO_SPEECH, b);
        this.mEditor.commit();
    }
    
    public void setVideoQuality(final int n) {
        this.mEditor.putInt(this.VIDEO_QUALITY, n);
        this.mEditor.commit();
    }
    
    public void setWaterMark(final boolean b) {
        this.mEditor.putBoolean(this.WATERMARK, b);
        this.mEditor.commit();
    }
    
    public void setWhiteBalanceValue(final int n) {
        this.mEditor.putInt(this.WHITEBALANCE, n);
        this.mEditor.commit();
    }
    
    public void setWorkStatus(final int n) {
        this.mEditor.putInt(this.WORK_STATUS, n);
        this.mEditor.commit();
    }
}
