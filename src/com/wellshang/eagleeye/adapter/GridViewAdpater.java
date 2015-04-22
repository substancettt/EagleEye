package com.wellshang.eagleeye.adapter;

import java.util.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.wellshang.eagleeye.storage.*;
import android.graphics.*;

public class GridViewAdpater extends BaseAdapter
{
    private ArrayList<String[]> DateList;
    private Context context;
    private LayoutInflater inflater;
    private int isSelect;
    
    public GridViewAdpater(final Context context, final ArrayList<String[]> dateList, final int isSelect) {
        this.context = context;
        this.DateList = dateList;
        this.isSelect = isSelect;
        this.inflater = LayoutInflater.from(context);
    }
    
    public int getCount() {
        return this.DateList.size();
    }
    
    public Object getItem(final int n) {
        return this.DateList.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        Holder tag;
        if (inflate == null) {
            tag = new Holder();
            inflate = this.inflater.inflate(2130903056, (ViewGroup)null);
            tag.img = (ImageView)inflate.findViewById(2131361843);
            tag.tv = (TextView)inflate.findViewById(2131361844);
            tag.playImg = (ImageView)inflate.findViewById(2131361845);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (Holder)inflate.getTag();
        }
        Bitmap imageBitmap;
        if (this.isSelect == 2) {
            tag.playImg.setVisibility(8);
            imageBitmap = Util.getBitmapFromFile(this.DateList.get(n)[0], 230, 150);
        }
        else {
            tag.playImg.setVisibility(0);
            imageBitmap = FileStorage.getVideoBitMapByContentResolver(this.context.getContentResolver(), 0, this.DateList.get(n)[0]);
        }
        tag.img.setImageBitmap(imageBitmap);
        tag.tv.setText((CharSequence)this.DateList.get(n)[1]);
        return inflate;
    }
    
    private class Holder
    {
        private ImageView img;
        private ImageView playImg;
        private TextView tv;
    }
}
