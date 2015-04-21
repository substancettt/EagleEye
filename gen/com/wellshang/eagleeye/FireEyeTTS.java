package com.wellshang.eagleeye;

import android.speech.tts.*;
import android.content.res.*;

import java.util.*;

public final class FireEyeTTS
{
    public static String AUTO_POWER_OFF_EIGHT;
    public static String AUTO_POWER_OFF_FIVE;
    public static String AUTO_POWER_OFF_FOUR;
    public static String AUTO_POWER_OFF_NINE;
    public static String AUTO_POWER_OFF_ONE;
    public static String AUTO_POWER_OFF_SEVEN;
    public static String AUTO_POWER_OFF_SIX;
    public static String AUTO_POWER_OFF_START;
    public static String AUTO_POWER_OFF_TEN;
    public static String AUTO_POWER_OFF_THREE;
    public static String AUTO_POWER_OFF_TWO;
    public static String AUTO_RECORDING;
    public static String CAR_REVERSING;
    public static String DELAY_SHUT_DOWN;
    public static String FILE_LOCK;
    public static String FIRE_EYE_START;
    public static String FLOAT_BTN_OPEN;
    public static String FLOAT_BTN_OPEN_FILE;
    public static String FORMAT_SD_CARD;
    public static String INSERT_SDCARD_BEFORE_RECORDING;
    public static String IS_MEDIA_SCANNER;
    public static String IS_RECORDING_BEFORE_ERROR;
    public static String IS_RECORDING_STATUS_NOW;
    public static String MINI_FIRE_EYE_OPEN;
    public static String MOTION_DETECTING;
    public static String MOTION_DETECT_CLOSE;
    public static String MOTION_DETECT_OPEN;
    public static String NOT_ENOUGH_SPACE;
    public static String NO_STORAGE;
    public static String NO_STORAGE_MOTION;
    public static String RECORD_MODE_VALUE_BACK;
    public static String RECORD_MODE_VALUE_FRONT;
    public static String RECORD_MODE_VALUE_TWO;
    public static String RECORD_MUTE_CLOSE;
    public static String RECORD_MUTE_OPEN;
    public static String SPEED_WATER_MARK_CLOSE;
    public static String SPEED_WATER_MARK_OPEN;
    public static String START_RECORDING;
    public static String STOP_RECORDING;
    public static String TIME_WATER_MARK_CLOSE;
    public static String TIME_WATER_MARK_OPEN;
    public static String UN_CAUGHT_EXCEPTION;
    public static String WILL_AUTO_POWER_OFF;
    public static String WORKS_STATUS_RECORDING;
    public static String WORKS_STATUS_TAKE_PICTURE;
    public static boolean isFloatBtnFirstStart;
    public static boolean isMiniFireEyeFirstStart;
    private static boolean isUsingTTS;
    public static TextToSpeech mTTS;
    
    static {
        FireEyeTTS.mTTS = null;
        FireEyeTTS.isMiniFireEyeFirstStart = true;
        FireEyeTTS.isFloatBtnFirstStart = true;
    }
    
    public static void StartUseTTS() {
        FireEyeTTS.isUsingTTS = true;
    }
    
    public static void StopUseTTS() {
        FireEyeTTS.isUsingTTS = false;
    }
    
    public static void init(final Resources resources) {
        FireEyeTTS.FIRE_EYE_START = resources.getString(2131165307);
        FireEyeTTS.RECORD_MODE_VALUE_TWO = resources.getString(2131165308);
        FireEyeTTS.RECORD_MODE_VALUE_FRONT = resources.getString(2131165309);
        FireEyeTTS.RECORD_MODE_VALUE_BACK = resources.getString(2131165310);
        FireEyeTTS.START_RECORDING = resources.getString(2131165311);
        FireEyeTTS.STOP_RECORDING = resources.getString(2131165312);
        FireEyeTTS.AUTO_RECORDING = resources.getString(2131165313);
        FireEyeTTS.MOTION_DETECTING = resources.getString(2131165314);
        FireEyeTTS.WORKS_STATUS_RECORDING = resources.getString(2131165315);
        FireEyeTTS.WORKS_STATUS_TAKE_PICTURE = resources.getString(2131165316);
        FireEyeTTS.AUTO_POWER_OFF_START = resources.getString(2131165317);
        FireEyeTTS.WILL_AUTO_POWER_OFF = resources.getString(2131165318);
        FireEyeTTS.CAR_REVERSING = resources.getString(2131165319);
        FireEyeTTS.TIME_WATER_MARK_OPEN = resources.getString(2131165320);
        FireEyeTTS.TIME_WATER_MARK_CLOSE = resources.getString(2131165321);
        FireEyeTTS.SPEED_WATER_MARK_OPEN = resources.getString(2131165322);
        FireEyeTTS.SPEED_WATER_MARK_CLOSE = resources.getString(2131165323);
        FireEyeTTS.RECORD_MUTE_OPEN = resources.getString(2131165324);
        FireEyeTTS.RECORD_MUTE_CLOSE = resources.getString(2131165325);
        FireEyeTTS.MOTION_DETECT_OPEN = resources.getString(2131165326);
        FireEyeTTS.MOTION_DETECT_CLOSE = resources.getString(2131165327);
        FireEyeTTS.MINI_FIRE_EYE_OPEN = resources.getString(2131165328);
        FireEyeTTS.FLOAT_BTN_OPEN = resources.getString(2131165329);
        FireEyeTTS.FLOAT_BTN_OPEN_FILE = resources.getString(2131165330);
        FireEyeTTS.FORMAT_SD_CARD = resources.getString(2131165331);
        FireEyeTTS.FILE_LOCK = resources.getString(2131165332);
        FireEyeTTS.NOT_ENOUGH_SPACE = resources.getString(2131165333);
        FireEyeTTS.NO_STORAGE = resources.getString(2131165334);
        FireEyeTTS.NO_STORAGE_MOTION = resources.getString(2131165335);
        FireEyeTTS.INSERT_SDCARD_BEFORE_RECORDING = resources.getString(2131165336);
        FireEyeTTS.IS_RECORDING_STATUS_NOW = resources.getString(2131165337);
        FireEyeTTS.UN_CAUGHT_EXCEPTION = resources.getString(2131165338);
        FireEyeTTS.IS_RECORDING_BEFORE_ERROR = resources.getString(2131165339);
        FireEyeTTS.IS_MEDIA_SCANNER = resources.getString(2131165340);
        FireEyeTTS.AUTO_POWER_OFF_TEN = resources.getString(2131165341);
        FireEyeTTS.AUTO_POWER_OFF_NINE = resources.getString(2131165342);
        FireEyeTTS.AUTO_POWER_OFF_EIGHT = resources.getString(2131165343);
        FireEyeTTS.AUTO_POWER_OFF_SEVEN = resources.getString(2131165344);
        FireEyeTTS.AUTO_POWER_OFF_SIX = resources.getString(2131165345);
        FireEyeTTS.AUTO_POWER_OFF_FIVE = resources.getString(2131165346);
        FireEyeTTS.AUTO_POWER_OFF_FOUR = resources.getString(2131165347);
        FireEyeTTS.AUTO_POWER_OFF_THREE = resources.getString(2131165348);
        FireEyeTTS.AUTO_POWER_OFF_TWO = resources.getString(2131165349);
        FireEyeTTS.AUTO_POWER_OFF_ONE = resources.getString(2131165350);
        FireEyeTTS.DELAY_SHUT_DOWN = resources.getString(2131165351);
    }
    
    public static boolean isUsingTTS() {
        return FireEyeTTS.isUsingTTS;
    }
    
    public static void release() {
        FireEyeTTS.mTTS.stop();
        FireEyeTTS.mTTS.shutdown();
        FireEyeTTS.mTTS = null;
    }
    
    public static void speakChinese(final String s) {
        if (FireEyeTTS.isUsingTTS && FireEyeTTS.mTTS != null) {
            FireEyeTTS.mTTS.speak(s, 0, (HashMap<String, String>)null);
        }
    }
    
    public static void speakChineseAfterStop(final String s) {
        if (FireEyeTTS.isUsingTTS && FireEyeTTS.mTTS != null) {
            FireEyeTTS.mTTS.speak(s, 1, (HashMap<String, String>)null);
        }
    }
}
