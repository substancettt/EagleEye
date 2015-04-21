package com.wellshang.eagleeye.adapter;

import java.util.*;
import android.content.*;
import com.wellshang.eagleeye.*;
import android.view.*;
import android.widget.*;

public class ListSwitchAdapterFrist extends BaseAdapter
{
    private ArrayList<HashMap<String, Object>> ListSwitch;
    private int[] image_id;
    private int[] image_id1;
    private int[] image_id2;
    LayoutInflater inflater;
    protected FireEyeConfig mConfig;
    private Context mContext;
    private HashMap<Integer, Boolean> swicthState;
    
    public ListSwitchAdapterFrist(final Context mContext) {
        final boolean b = true;
        boolean b2 = true;
        this.mContext = mContext;
        this.mConfig = FireEyeShare.getFireEyeConfig(mContext);
        this.inflater = LayoutInflater.from(this.mContext);
        this.swicthState = new HashMap<Integer, Boolean>();
        this.image_id1 = new int[] { 2130837595, 2130837534, 2130837574, 2130837523, 2130837570, 2130837574, 2130837589 };
        this.image_id2 = new int[] { 2130837595, 2130837534, 2130837574, 2130837523, 2130837570, 2130837574, 2130837589, 2130837569 };
        if (this.mConfig.getRecordModeValue() == 0) {
            final int[] array2;
            final int[] array = array2 = new int[8];
            array2[0] = 2131165205;
            array2[1] = 2131165206;
            array2[2] = 2131165207;
            array2[3] = 2131165211;
            array2[4] = 2131165219;
            array2[5] = 2131165220;
            array2[6] = 2131165222;
            array2[7] = 2131165209;
            final int length = array.length;
            this.ListSwitch = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < length; ++i) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("ItemText", array[i]);
                this.ListSwitch.add(hashMap);
            }
            this.image_id = this.image_id2;
            this.swicthState.put(0, this.mConfig.getWaterMark());
            this.swicthState.put(1, this.mConfig.getMotionDetect());
            this.swicthState.put(2, this.mConfig.getRecordMute());
            this.swicthState.put(3, this.mConfig.getAutoRecording());
            this.swicthState.put(4, this.mConfig.getFireEyeAutoStart());
            this.swicthState.put(5, this.mConfig.getTextToSpeech());
            final HashMap<Integer, Boolean> swicthState = this.swicthState;
            if (this.mConfig.getInitBack() != 1) {
                b2 = false;
            }
            swicthState.put(6, b2);
            this.swicthState.put(7, this.mConfig.getPIPStatus());
            return;
        }
        final int[] array4;
        final int[] array3 = array4 = new int[7];
        array4[0] = 2131165205;
        array4[1] = 2131165206;
        array4[2] = 2131165207;
        array4[3] = 2131165211;
        array4[4] = 2131165219;
        array4[5] = 2131165220;
        array4[6] = 2131165222;
        final int length2 = array3.length;
        this.ListSwitch = new ArrayList<HashMap<String, Object>>();
        for (int j = 0; j < length2; ++j) {
            final HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
            hashMap2.put("ItemText", array3[j]);
            this.ListSwitch.add(hashMap2);
        }
        this.image_id = this.image_id1;
        this.swicthState.put(0, this.mConfig.getWaterMark());
        this.swicthState.put(1, this.mConfig.getMotionDetect());
        this.swicthState.put(2, this.mConfig.getRecordMute());
        this.swicthState.put(3, this.mConfig.getAutoRecording());
        this.swicthState.put(4, this.mConfig.getFireEyeAutoStart());
        this.swicthState.put(5, this.mConfig.getTextToSpeech());
        this.swicthState.put(6, this.mConfig.getInitBack() == 1 && b);
    }
    
    public int getCount() {
        return this.ListSwitch.size();
    }
    
    public Object getItem(final int n) {
        return this.ListSwitch.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        ViewHolderFrist tag;
        if (inflate == null) {
            tag = new ViewHolderFrist();
            inflate = this.inflater.inflate(2130903045, (ViewGroup)null);
            tag.sImage = (ImageView)inflate.findViewById(2131361808);
            tag.title = (TextView)inflate.findViewById(2131361809);
            (tag.viewSth = (CheckBox)inflate.findViewById(2131361810)).setVisibility(4);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolderFrist)inflate.getTag();
        }
        tag.sImage.setImageResource(this.image_id[n]);
//        tag.title.setText((int)this.ListSwitch.get(n).get("ItemText"));
        tag.title.setText("UNKNOWN TAG 0");
        tag.viewSth.setVisibility(0);
        tag.viewSth.setChecked((boolean)this.swicthState.get(n));
        tag.viewSth.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                ListSwitchAdapterFrist.this.saveItemSetValue(n, b);
            }
        });
        return inflate;
    }
    
    protected void saveItemSetValue(final int n, final boolean pipStatus) {
        switch (n) {
            case 0:
                this.mConfig.setWaterMark(pipStatus);
            case 1:
                this.mConfig.setMotionDetect(pipStatus);
            case 2:
                this.mConfig.setRecordMute(pipStatus);
            case 3:
                this.mConfig.setAutoRecording(pipStatus);
            case 4:
                this.mConfig.setFireEyeAutoStart(pipStatus);
            case 5:
                this.mConfig.setTextToSpeech(pipStatus);
            case 7:
                if (this.mConfig.getRecordModeValue() == 0) {
                    this.mConfig.setPIPStatus(pipStatus);
                    return;
                }
                break;
            case 6:
                if (pipStatus) {
                    this.mConfig.setInitBack(1);
                    return;
                }
                this.mConfig.setInitBack(0);
        }
    }
    
    public final class ViewHolderFrist
    {
        public ImageView sImage;
        public TextView title;
        public CheckBox viewSth;
    }
}
