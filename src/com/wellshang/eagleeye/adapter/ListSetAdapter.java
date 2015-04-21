package com.wellshang.eagleeye.adapter;

import java.util.*;

import android.content.*;

import com.wellshang.eagleeye.*;

import android.graphics.*;
import android.view.*;
import android.widget.*;

import com.wellshang.eagleeye.main.*;

public class ListSetAdapter extends BaseAdapter
{
    private ArrayList<Bitmap> bitmapsList;
    private HashMap<Integer, Boolean> checkState;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listSet;
    private int mCameraMode;
    protected FireEyeConfig mConfig;
    private Context mContext;
    private int mSelectItem;
    protected int mSettingIndex;
    private int mTotalCount;
    
    public ListSetAdapter(final Context mContext, final int mSettingIndex, final int mCameraMode) {
        this.mSelectItem = 0;
        this.mTotalCount = 0;
        this.mCameraMode = 0;
        this.mSettingIndex = 0;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(this.mContext);
        this.checkState = new HashMap<Integer, Boolean>();
        this.mConfig = FireEyeShare.getFireEyeConfig(mContext);
        this.mSettingIndex = mSettingIndex;
        this.mCameraMode = mCameraMode;
        switch (this.mSettingIndex) {
            default:
            case 0:
                this.loadWhiteBalanceValue();
            case 1:
                this.loadExposureValue();
            case 2:
                this.loadColorModeValue();
            case 3:
                this.loadRecordTimeValue();
            case 4:
                if (this.mCameraMode == 0) {
                    this.loadVideoQualityValue();
                    return;
                }
                this.loadPhotoQualityValue();
            case 5:
                this.loadRecordModeValue();
        }
    }
    
    @SuppressWarnings("unused")
    private void loadBrightNessValue() {
        final int[] array2;
        final int[] array = array2 = new int[5];
        array2[0] = 2131165235;
        array2[1] = 2131165236;
        array2[2] = 2131165237;
        array2[3] = 2131165238;
        array2[4] = 2131165239;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getBrightNessValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
    }
    
    private void loadColorModeImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837537));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837536));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837540));
        this.bitmapsList.add(3, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837538));
        this.bitmapsList.add(4, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837541));
        this.bitmapsList.add(5, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837539));
    }
    
    private void loadColorModeValue() {
        final int[] array2;
        final int[] array = array2 = new int[6];
        array2[0] = 2131165241;
        array2[1] = 2131165242;
        array2[2] = 2131165243;
        array2[3] = 2131165245;
        array2[4] = 2131165247;
        array2[5] = 2131165248;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getColorModeValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadColorModeImage();
    }
    
    private void loadExposureImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837558));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837559));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837560));
        this.bitmapsList.add(3, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837561));
        this.bitmapsList.add(4, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837562));
    }
    
    private void loadExposureValue() {
        final int[] array2;
        final int[] array = array2 = new int[5];
        array2[0] = 2131165229;
        array2[1] = 2131165230;
        array2[2] = 2131165231;
        array2[3] = 2131165232;
        array2[4] = 2131165233;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getExposureValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadExposureImage();
    }
    
    private void loadPhotoQualityImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837611));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837612));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837613));
        this.bitmapsList.add(3, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837614));
    }
    
    private void loadPhotoQualityValue() {
        final int[] array2;
        final int[] array = array2 = new int[4];
        array2[0] = 2131165267;
        array2[1] = 2131165268;
        array2[2] = 2131165269;
        array2[3] = 2131165270;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getPhotoQuality();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadPhotoQualityImage();
    }
    
    private void loadRecordModeImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837565));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837564));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837563));
    }
    
    private void loadRecordModeValue() {
        final int[] array2;
        final int[] array = array2 = new int[3];
        array2[0] = 2131165259;
        array2[1] = 2131165261;
        array2[2] = 2131165260;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getRecordModeValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadRecordModeImage();
    }
    
    private void loadRecordTimeImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837544));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837545));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837546));
        this.bitmapsList.add(3, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837542));
    }
    
    private void loadRecordTimeValue() {
        final int[] array2;
        final int[] array = array2 = new int[4];
        array2[0] = 2131165253;
        array2[1] = 2131165254;
        array2[2] = 2131165255;
        array2[3] = 2131165257;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getRecordTimeValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadRecordTimeImage();
    }
    
    private void loadVideoQualityImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837550));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837549));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837548));
    }
    
    private void loadVideoQualityValue() {
        final int[] array2;
        final int[] array = array2 = new int[3];
        array2[0] = 2131165263;
        array2[1] = 2131165264;
        array2[2] = 2131165265;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getVideoQuality();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadVideoQualityImage();
    }
    
    private void loadWhiteBalanceImage() {
        (this.bitmapsList = new ArrayList<Bitmap>()).add(0, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837552));
        this.bitmapsList.add(1, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837556));
        this.bitmapsList.add(2, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837554));
        this.bitmapsList.add(3, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837553));
        this.bitmapsList.add(4, BitmapFactory.decodeResource(this.mContext.getResources(), 2130837555));
    }
    
    private void loadWhiteBalanceValue() {
        final int[] array2;
        final int[] array = array2 = new int[5];
        array2[0] = 2131165223;
        array2[1] = 2131165227;
        array2[2] = 2131165225;
        array2[3] = 2131165224;
        array2[4] = 2131165226;
        this.listSet = new ArrayList<HashMap<String, Object>>();
        this.mTotalCount = array.length;
        this.mSelectItem = this.mConfig.getWhiteBalanceValue();
        for (int i = 0; i < this.mTotalCount; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.listSet.add(hashMap);
            if (this.mSelectItem == i) {
                this.checkState.put(i, true);
            }
            else {
                this.checkState.put(i, false);
            }
        }
        this.loadWhiteBalanceImage();
    }
    
    public int getCount() {
        return this.listSet.size();
    }
    
    public Object getItem(final int n) {
        return this.listSet.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int id, View inflate, final ViewGroup viewGroup) {
        inflate = this.inflater.inflate(2130903044, (ViewGroup)null);
        final RadioButton radioButton = (RadioButton)inflate.findViewById(2131361805);
        ((ImageView)inflate.findViewById(2131361806)).setImageBitmap((Bitmap)this.bitmapsList.get(id));
//        radioButton.setText(this.listSet.get(id).get("ItemText"));
      radioButton.setText("UNKNOWN");
        radioButton.setId(id);
        radioButton.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                if (b && ListSetAdapter.this.mSelectItem != id) {
                    if (ListSetAdapter.this.mSelectItem != -1) {
                        final RadioButton radioButton = (RadioButton)((FireEye)ListSetAdapter.this.mContext).findViewById(ListSetAdapter.this.mSelectItem);
                        if (radioButton != null) {
                            radioButton.setChecked(false);
                        }
                        ListSetAdapter.this.checkState.put(ListSetAdapter.this.mSelectItem, false);
                    }
                    ListSetAdapter.this.mSelectItem = compoundButton.getId();
                    ListSetAdapter.this.saveItemSetValue(ListSetAdapter.this.mSelectItem);
                }
            }
        });
        if (this.mSelectItem == id) {
            radioButton.setChecked(true);
        }
        return inflate;
    }
    
    protected void saveItemSetValue(final int recordModeValue) {
        switch (this.mSettingIndex) {
            default:
            case 0:
                this.mConfig.setWhiteBalanceValue(recordModeValue);
            case 1:
                this.mConfig.setExposureValue(recordModeValue);
            case 2:
                this.mConfig.setColorModeValue(recordModeValue);
            case 3:
                this.mConfig.setRecordTimeValue(recordModeValue);
            case 4:
                if (this.mCameraMode == 0) {
                    this.mConfig.setVideoQuality(recordModeValue);
                    return;
                }
                this.mConfig.setPhotoQuality(recordModeValue);
            case 5:
                this.mConfig.setRecordModeValue(recordModeValue);
        }
    }
}
