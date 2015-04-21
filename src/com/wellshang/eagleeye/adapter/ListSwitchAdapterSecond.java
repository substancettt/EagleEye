package com.wellshang.eagleeye.adapter;

import java.util.*;

import android.content.*;

import com.wellshang.eagleeye.*;

import android.view.*;
import android.widget.*;

public class ListSwitchAdapterSecond extends BaseAdapter
{
    @SuppressWarnings("unused")
    private static String TAG;
    private ArrayList<HashMap<String, Object>> ListSwitch;
    private int[] image_id;
    LayoutInflater inflater;
    protected FireEyeConfig mConfig;
    private Context mContext;
    private HashMap<Integer, Boolean> swicthState;
    
    static {
        ListSwitchAdapterSecond.TAG = "ListSwitchAdapter";
    }
    
    public ListSwitchAdapterSecond(final Context mContext) {
        this.mContext = mContext;
        this.mConfig = FireEyeShare.getFireEyeConfig(mContext);
        this.inflater = LayoutInflater.from(this.mContext);
        this.swicthState = new HashMap<Integer, Boolean>();
        this.image_id = new int[] { 2130837570, 2130837532, 2130837529, 2130837527, 2130837534, 2130837571, 2130837595, 2130837532 };
        final int[] array2;
        final int[] array = array2 = new int[8];
        array2[0] = 2131165212;
        array2[1] = 2131165214;
        array2[2] = 2131165215;
        array2[3] = 2131165208;
        array2[4] = 2131165217;
        array2[5] = 2131165218;
        array2[6] = 2131165216;
        array2[7] = 2131165352;
        final int length = array.length;
        this.ListSwitch = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; ++i) {
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("ItemText", array[i]);
            this.ListSwitch.add(hashMap);
        }
        this.swicthState.put(0, this.mConfig.getPowerDelay());
        this.swicthState.put(1, this.mConfig.getLightFreq());
        this.swicthState.put(2, true);
        this.swicthState.put(3, this.mConfig.getDlnaShare());
        this.swicthState.put(4, true);
        this.swicthState.put(5, this.mConfig.getAutoPowerOff());
        this.swicthState.put(6, this.mConfig.getSpeedMark());
        this.swicthState.put(7, this.mConfig.getLedLight());
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
        ViewHolderSecond tag;
        if (inflate == null) {
            tag = new ViewHolderSecond();
            inflate = this.inflater.inflate(2130903045, (ViewGroup)null);
            tag.sImage = (ImageView)inflate.findViewById(2131361808);
            tag.title = (TextView)inflate.findViewById(2131361809);
            (tag.viewSth = (CheckBox)inflate.findViewById(2131361810)).setVisibility(4);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolderSecond)inflate.getTag();
        }
        if (n == 2) {
            tag.sImage.setImageResource(this.image_id[2]);
//            tag.title.setText((int)this.ListSwitch.get(n).get("ItemText"));
            tag.title.setText("UNKNOWN TAG 1");
            tag.viewSth.setVisibility(4);
            return inflate;
        }
        if (n == 4) {
            tag.sImage.setImageResource(this.image_id[4]);
//            tag.title.setText((int)this.ListSwitch.get(n).get("ItemText"));
            tag.title.setText("UNKNOWN TAG 2");
            tag.viewSth.setVisibility(4);
            return inflate;
        }
        tag.sImage.setImageResource(this.image_id[n]);
//        tag.title.setText((int)this.ListSwitch.get(n).get("ItemText"));
        tag.title.setText("UNKNOWN TAG 3");
        tag.viewSth.setVisibility(0);
        tag.viewSth.setChecked((boolean)this.swicthState.get(n));
        tag.viewSth.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                ListSwitchAdapterSecond.this.saveItemSetValue(n, b);
            }
        });
        return inflate;
    }
    
    protected void saveItemSetValue(final int n, final boolean b) {
        switch (n) {
            default:
            case 0:
                this.mConfig.setPowerDelay(b);
            case 1:
                this.mConfig.setLightFreq(b);
            case 3:
                this.mConfig.setDlnaShare(b);
            case 5:
                this.mConfig.setAutoPowerOff(b);
            case 6:
                this.mConfig.setSpeedMark(b);
            case 7:
                this.mConfig.setLedLight(b);
        }
    }
    
    public final class ViewHolderSecond
    {
        public ImageView sImage;
        public TextView title;
        public CheckBox viewSth;
    }
}
