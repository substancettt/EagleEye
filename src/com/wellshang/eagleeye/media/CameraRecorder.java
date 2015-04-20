package com.wellshang.eagleeye.media;

import android.util.*;
import java.lang.ref.*;
import java.io.*;
import android.view.*;
import android.os.*;

public class CameraRecorder
{
    private static final String TAG = "CameraRecorder";
    private EventHandler mEventHandler;
    private FileDescriptor mFd;
    private int mNativeContext;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private String mPath;
    private PictureCallback mPictureCallback;
    private ShutterCallback mShutterCallback;
    private Surface mSurface;
    
    static {
        System.loadLibrary("CameraRecorder_jni");
        _native_init();
    }
    
    public CameraRecorder() {
        final Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        }
        else {
            final Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            }
            else {
                this.mEventHandler = null;
            }
        }
        Log.d("CameraRecorder", "CameraRecorder");
        this._native_setup(new WeakReference(this));
    }
    
    private final native void _addWaterMarkSource(final int p0, final String p1, final int p2);
    
    private final native int _getChroma(final int p0);
    
    private final native int _getExposure(final int p0);
    
    private final native int _getLineFrequency(final int p0);
    
    private final native int _getMotionStatus(final int p0);
    
    private final native int _getRecordMute(final int p0);
    
    private final native int _getWhiteBalance(final int p0);
    
    private final native void _initBackCamera(final int p0, final int p1);
    
    private final native void _native_finalize();
    
    private static final native void _native_init();
    
    private final native void _native_setup(final Object p0);
    
    private native void _prepare(final int p0) throws IllegalStateException;
    
    private final native void _refreshWaterMark(final int p0, final int p1, final int p2, final int[] p3, final int p4);
    
    private final native void _setAudioEncode(final int p0);
    
    private final native void _setAudioEncoder(final int p0);
    
    private final native void _setAudioEncodingBitRate(final int p0, final int p1);
    
    private final native void _setAudioSampleRate(final int p0, final int p1);
    
    private final native void _setAudioSource(final int p0);
    
    private final native void _setChroma(final int p0, final int p1);
    
    private final native void _setExposure(final int p0, final int p1);
    
    private final native void _setLineFrequency(final int p0, final int p1);
    
    private final native int _setMotionParam(final int p0, final int p1, final int p2);
    
    private native void _setOutputFile(final String p0, final int p1) throws IllegalStateException;
    
    private final native void _setOutputFormat(final int p0, final int p1);
    
    private final native void _setOverlaySrc(final int p0, final int p1);
    
    private native void _setPhotoSize(final int p0, final int p1, final int p2);
    
    private final native void _setPreviewDisplay(final Surface p0, final int p1) throws IOException;
    
    private final native void _setRecordMute(final int p0, final int p1);
    
    private final native void _setVideoEncode(final int p0);
    
    private final native void _setVideoEncoder(final int p0);
    
    private native void _setVideoEncodingBitRate(final int p0, final int p1);
    
    private native void _setVideoFrameRate(final int p0, final int p1);
    
    private native void _setVideoSize(final int p0, final int p1, final int p2);
    
    private final native void _setVideoSource(final int p0);
    
    private final native void _setWaterMarkStatus(final int p0, final int p1);
    
    private final native void _setWhiteBalance(final int p0, final int p1);
    
    private final native void _smartShot(final int p0);
    
    private native void _start(final int p0) throws IllegalStateException;
    
    private final native void _startCamera(final int p0);
    
    private final native void _startPreview(final int p0);
    
    private native void _stop(final int p0) throws IllegalStateException;
    
    private final native void _stopCamera(final int p0);
    
    private final native void _stopPreview(final int p0);
    
    private final native void _takePicture(final int p0);
    
    public void addWaterMarkSource(final String s, final int n, final int n2) {
        this._addWaterMarkSource(n, s, n2);
    }
    
    public final void finalizeCameraRecorder() {
        this._native_finalize();
    }
    
    public int getChroma(final int n) {
        return this._getChroma(n);
    }
    
    public int getExposure(final int n) {
        return this._getExposure(n);
    }
    
    public int getLineFrequency(final int n) {
        return this._getLineFrequency(n);
    }
    
    public int getMotionStatus(final int n) {
        return this._getMotionStatus(n);
    }
    
    public int getRecordMute(final int n) {
        return this._getRecordMute(n);
    }
    
    public int getWhiteBalance(final int n) {
        return this._getWhiteBalance(n);
    }
    
    public void initBackCamera(final int n, final int n2) {
        this._initBackCamera(n, n2);
    }
    
    public void prepareRecord(final int n) {
        this._prepare(n);
    }
    
    public void refreshWaterMark(final int n, final int n2, final int n3, final int[] array, final int n4) {
        this._refreshWaterMark(n, n2, n3, array, n4);
    }
    
    public void setAudioEncode(final int n) {
        this._setAudioEncode(n);
    }
    
    public void setAudioSource(final int n) {
        this._setAudioSource(n);
    }
    
    public void setBrightness(final int n, final int n2) {
    }
    
    public void setChroma(final int n, final int n2) {
        this._setChroma(n, n2);
    }
    
    public void setContrast(final int n, final int n2) {
    }
    
    public void setExposure(final int n, final int n2) {
        Log.d("CameraRecorder", "----------exp = " + n);
        this._setExposure(n, n2);
    }
    
    public void setLineFrequency(final int n, final int n2) {
        this._setLineFrequency(n, n2);
    }
    
    public int setMotionParam(final int n, final int n2, final int n3) {
        return this._setMotionParam(n, n2, n3);
    }
    
    public void setOnErrorListener(final OnErrorListener mOnErrorListener) {
        this.mOnErrorListener = mOnErrorListener;
    }
    
    public void setOnInfoListener(final OnInfoListener mOnInfoListener) {
        this.mOnInfoListener = mOnInfoListener;
    }
    
    public void setOutputFile(final String s, final int n) {
        this._setOutputFile(s, n);
    }
    
    public void setOutputFormat(final int n, final int n2) {
        this._setOutputFormat(n, n2);
    }
    
    public void setOverlaySrc(final int n, final int n2) {
        this._setOverlaySrc(n, n2);
    }
    
    public void setPhotoQuality(final int n, final int n2) {
        if (n == 0) {
            this._setPhotoSize(640, 480, n2);
        }
        else {
            if (n == 1) {
                this._setPhotoSize(1280, 960, n2);
                return;
            }
            if (n == 2) {
                this._setPhotoSize(1600, 1200, n2);
                return;
            }
            if (n == 3) {
                this._setPhotoSize(2048, 1536, n2);
                return;
            }
            if (n == 4) {
                this._setPhotoSize(2592, 1936, n2);
            }
        }
    }
    
    public final void setPreviewDisplay(final SurfaceHolder surfaceHolder, final int n) {
        if (surfaceHolder != null) {
            Log.d("CameraRecorder", "holder.getSurface() = " + surfaceHolder.getSurface());
            try {
                this._setPreviewDisplay(surfaceHolder.getSurface(), n);
                return;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
        try {
            this._setPreviewDisplay(null, n);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void setRecordMute(final int n, final int n2) {
        this._setRecordMute(n, n2);
    }
    
    public void setSaturation(final int n, final int n2) {
    }
    
    public void setVideoEncode(final int n) {
        this._setVideoEncode(n);
    }
    
    public void setVideoEncodingBitRate(final int n, final int n2) {
        this._setVideoEncodingBitRate(n, n2);
    }
    
    public void setVideoFrameRate(final int n, final int n2) {
        this._setVideoFrameRate(n, n2);
    }
    
    public void setVideoQuality(final int n, final int n2) {
        if (n == 0) {
            this._setVideoSize(640, 480, n2);
            this.setVideoEncodingBitRate(2000000, n2);
        }
        else {
            if (n == 3) {
                this._setVideoSize(720, 480, n2);
                this.setVideoEncodingBitRate(2000000, n2);
                return;
            }
            if (n == 4) {
                this._setVideoSize(720, 576, n2);
                this.setVideoEncodingBitRate(3000000, n2);
                return;
            }
            if (n == 1) {
                this._setVideoSize(1280, 720, n2);
                this.setVideoEncodingBitRate(7000000, n2);
                return;
            }
            if (n == 2) {
                this._setVideoSize(1920, 1080, n2);
                this.setVideoEncodingBitRate(8500000, n2);
            }
        }
    }
    
    public void setVideoSource(final int n) {
        this._setVideoSource(n);
    }
    
    public void setWaterMark(final int n, final int n2) {
        this._setWaterMarkStatus(n, n2);
    }
    
    public void setWhiteBalance(final int n, final int n2) {
        this._setWhiteBalance(n, n2);
    }
    
    public void smartShot(final int n) {
        this._smartShot(n);
    }
    
    public void startCamera(final int n) {
        this._startCamera(n);
    }
    
    public void startPreview(final int n) {
        this._startPreview(n);
    }
    
    public void startRecord(final int n) {
        this._start(n);
    }
    
    public void stopCamera(final int n) {
        this._stopCamera(n);
    }
    
    public void stopPreview(final int n) {
        this._stopPreview(n);
    }
    
    public void stopRecord(final int n) {
        this._stop(n);
    }
    
    public void takePicture(final ShutterCallback mShutterCallback, final PictureCallback mPictureCallback, final int n) {
        this.mShutterCallback = mShutterCallback;
        this.mPictureCallback = mPictureCallback;
        this._takePicture(n);
    }
    
    public final class AudioEncoder
    {
        public static final int AAC = 3;
        public static final int AAC_PLUS = 4;
        public static final int AMR_NB = 1;
        public static final int AMR_WB = 2;
        public static final int DEFAULT = 0;
        public static final int EAAC_PLUS = 5;
    }
    
    public final class AudioSource
    {
        public static final int CAMCORDER = 5;
        public static final int DEFAULT = 0;
        public static final int MIC = 1;
        public static final int VOICE_CALL = 4;
        public static final int VOICE_COMMUNICATION = 7;
        public static final int VOICE_DOWNLINK = 3;
        public static final int VOICE_RECOGNITION = 6;
        public static final int VOICE_UPLINK = 2;
    }
    
    public final class Brightness
    {
    }
    
    public final class Chroma
    {
        public static final int CHROMA_BLACKBOARD = 7;
        public static final int CHROMA_MONO = 1;
        public static final int CHROMA_NEGATIVE = 2;
        public static final int CHROMA_NONE = 0;
        public static final int CHROMA_POSTERIZE = 5;
        public static final int CHROMA_SEPIA = 4;
        public static final int CHROMA_SOLARIZE = 3;
        public static final int CHROMA_WHITEBOARD = 6;
    }
    
    public final class Contrast
    {
    }
    
    public final class EncodingBitRate
    {
        public static final int BIT_RATE_1M = 1000000;
        public static final int BIT_RATE_2M = 2000000;
        public static final int BIT_RATE_3M = 3000000;
        public static final int BIT_RATE_4M = 4000000;
        public static final int BIT_RATE_5M = 5000000;
        public static final int BIT_RATE_6M = 6000000;
        public static final int BIT_RATE_7M = 7000000;
        public static final int BIT_RATE_8M = 8000000;
        public static final int BIT_RATE_9M = 8500000;
    }
    
    private class EventHandler extends Handler
    {
        private static final int CAMERA_RECORDER_EVENT_ERROR = 1;
        private static final int CAMERA_RECORDER_EVENT_INFO = 2;
        private static final int CAMERA_RECORDER_EVENT_LIST_END = 99;
        private static final int CAMERA_RECORDER_EVENT_LIST_START = 1;
        private static final int CAMERA_RECORDER_TRACK_EVENT_ERROR = 100;
        private static final int CAMERA_RECORDER_TRACK_EVENT_INFO = 101;
        private static final int CAMERA_RECORDER_TRACK_EVENT_LIST_END = 1000;
        private static final int CAMERA_RECORDER_TRACK_EVENT_LIST_START = 100;
        private CameraRecorder mCameraRecorder;
        
        public EventHandler(final CameraRecorder mCameraRecorder, final Looper looper) {
            super(looper);
            this.mCameraRecorder = mCameraRecorder;
        }
        
        public void handleMessage(final Message message) {
            if (this.mCameraRecorder.mNativeContext == 0) {
                Log.w("CameraRecorder", "CameraRecorder went away with unhandled events");
            }
            else {
                switch (message.what) {
                    default: {
                        Log.e("CameraRecorder", "Unknown message type " + message.what);
                    }
                    case 1:
                    case 100: {
                        if (CameraRecorder.this.mOnErrorListener != null) {
                            CameraRecorder.this.mOnErrorListener.onError(this.mCameraRecorder, message.arg1, message.arg2);
                            return;
                        }
                        break;
                    }
                    case 2:
                    case 101: {
                        if (CameraRecorder.this.mOnInfoListener != null) {
                            CameraRecorder.this.mOnInfoListener.onInfo(this.mCameraRecorder, message.arg1, message.arg2);
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public final class Exposure
    {
        public static final int EXPOSURE_LEVEL1 = -4;
        public static final int EXPOSURE_LEVEL2 = -3;
        public static final int EXPOSURE_LEVEL3 = -2;
        public static final int EXPOSURE_LEVEL4 = -1;
        public static final int EXPOSURE_LEVEL5 = 0;
        public static final int EXPOSURE_LEVEL6 = 1;
        public static final int EXPOSURE_LEVEL7 = 2;
        public static final int EXPOSURE_LEVEL8 = 3;
        public static final int EXPOSURE_LEVEL9 = 4;
    }
    
    public final class LineFrequency
    {
        public static final int LINE_FREQUENCY_50HZ = 0;
        public static final int LINE_FREQUENCY_60HZ = 1;
    }
    
    public interface OnErrorListener
    {
        void onError(CameraRecorder p0, int p1, int p2);
    }
    
    public interface OnInfoListener
    {
        void onInfo(CameraRecorder p0, int p1, int p2);
    }
    
    public final class OutputFormat
    {
        public static final int FMT_AWTS = 1;
        public static final int FMT_MP4 = 0;
        public static final int FMT_RAW = 2;
        public static final int FMT_TS = 3;
    }
    
    public final class PhotoQuality
    {
        public static final int QUALITY_100W = 1;
        public static final int QUALITY_200W = 2;
        public static final int QUALITY_300W = 3;
        public static final int QUALITY_30W = 0;
        public static final int QUALITY_500W = 4;
        public static final int QUALITY_800W = 5;
    }
    
    public interface PictureCallback
    {
        void onPictureTaken(byte[] p0, int p1);
    }
    
    public final class Saturation
    {
    }
    
    public interface ShutterCallback
    {
        void onShutter();
    }
    
    public final class VideoEncoder
    {
        public static final int DEFAULT = 0;
        public static final int H263 = 1;
        public static final int H264 = 2;
        public static final int MPEG_4_SP = 3;
    }
    
    public final class VideoQuality
    {
        public static final int QUALITY_1080P = 2;
        public static final int QUALITY_480P = 3;
        public static final int QUALITY_576P = 4;
        public static final int QUALITY_720P = 1;
        public static final int QUALITY_VGA = 0;
    }
    
    public final class VideoSource
    {
        public static final int CAMERA = 1;
        public static final int DEFAULT = 0;
        public static final int GRALLOC_BUFFER = 2;
    }
    
    public final class WhiteBalance
    {
        public static final int WHITE_BALANCE_AUTO = 0;
        public static final int WHITE_BALANCE_CLOUDY_DAYLIGHT = 5;
        public static final int WHITE_BALANCE_DAYLIGHT = 4;
        public static final int WHITE_BALANCE_FLUORESCENT = 2;
        public static final int WHITE_BALANCE_INCANDESCENT = 1;
        public static final int WHITE_BALANCE_SHADE = 7;
        public static final int WHITE_BALANCE_TWILIGHT = 6;
        public static final int WHITE_BALANCE_WARM_FLUORESCENT = 3;
    }
}
