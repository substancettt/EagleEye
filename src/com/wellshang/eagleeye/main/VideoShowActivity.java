package com.wellshang.eagleeye.main;

import java.io.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import com.wellshang.eagleeye.adapter.*;
import com.wellshang.eagleeye.storage.*;

public class VideoShowActivity extends Activity
{
    private static boolean monthIsChance;
    private Handler UiHandler;
    private int[] date;
    private String day;
    private Button dayBtn;
    private ArrayList<String> dayList;
    private String frontBack;
    private Button frontBackBtn;
    private int frontBackSelect;
    private String[] front_back_list_data;
    private ArrayList<String> front_back_list_text;
    private GridViewAdpater gridViewAdpater;
    private boolean isNewSearch;
    private int isSelect;
    private ArrayList<String> isSelectList;
    private Button isTodayBtn;
    private ArrayList<String[]> list;
    private ListViewAdpter listViewAdpter;
    private ViewGroup mContainer;
    private String month;
    private Button monthBtn;
    private ArrayList<String> monthList;
    private ProgressBar progressBar;
    private Button selectBtn;
    private RelativeLayout set_data_rl;
    private TextView sorry_text;
    private Button switchBrn;
    private ArrayList<String[]> unLockDateList;
    private GridView videoShowGrid;
    private ListView videoshowListview;
    private int viewNum;
    private ArrayList<String> yearList;
    
    static {
        VideoShowActivity.monthIsChance = false;
    }
    
    public VideoShowActivity() {
        this.front_back_list_data = new String[] { "_", "0.mp4", "2.mp4", "0.jpg", "2.jpg" };
        this.yearList = null;
        this.monthList = null;
        this.dayList = null;
        this.isSelect = 0;
        this.frontBackSelect = 0;
        this.gridViewAdpater = null;
        this.listViewAdpter = null;
        this.isNewSearch = false;
        this.viewNum = 0;
        this.UiHandler = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    case -1:
                        VideoShowActivity.this.progressBar.setVisibility(0);
                        break;
                    case 0:
                        VideoShowActivity.monthIsChance = false;
                        VideoShowActivity.this.isNewSearch = false;
                        VideoShowActivity.this.progressBar.setVisibility(8);
                        VideoShowActivity.this.set_data_rl.setVisibility(0);
                        VideoShowActivity.this.setGridView(VideoShowActivity.this.list);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.selectBtn, VideoShowActivity.this.isSelectList, 0);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.monthBtn, VideoShowActivity.this.monthList, 2);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.dayBtn, VideoShowActivity.this.dayList, 3);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.frontBackBtn, VideoShowActivity.this.front_back_list_text, 4);
                        break;
                    case 1:
                        VideoShowActivity.this.progressBar.setVisibility(0);
                        VideoShowActivity.this.sorry_text.setVisibility(8);
                        new UpdataTh().start();
                        break;
                    case 2:
                        VideoShowActivity.monthIsChance = false;
                        VideoShowActivity.this.isNewSearch = false;
                        VideoShowActivity.this.progressBar.setVisibility(8);
                        VideoShowActivity.this.set_data_rl.setVisibility(0);
                        VideoShowActivity.this.setListView(VideoShowActivity.this.list);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.selectBtn, VideoShowActivity.this.isSelectList, 0);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.monthBtn, VideoShowActivity.this.monthList, 2);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.dayBtn, VideoShowActivity.this.dayList, 3);
                        VideoShowActivity.this.setButtonListen(VideoShowActivity.this.frontBackBtn, VideoShowActivity.this.front_back_list_text, 4);
                        break;
                    case 3:
                        VideoShowActivity.this.videoShowGrid.setVisibility(0);
                        VideoShowActivity.this.videoshowListview.setVisibility(8);
                        VideoShowActivity.this.setGridView(VideoShowActivity.this.list);
                        break;
                    case 4:
                        VideoShowActivity.this.videoShowGrid.setVisibility(8);
                        VideoShowActivity.this.videoshowListview.setVisibility(0);
                        VideoShowActivity.this.setListView(VideoShowActivity.this.list);
                        break;
                }
                super.handleMessage(message);
            }
        };
    }
    
    private void applyRotation(final int n, final float n2, final float n3) {
        final Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(n2, n3, this.mContainer.getWidth() / 2.0f, this.mContainer.getHeight() / 2.0f, 310.0f, true);
        rotate3dAnimation.setDuration(500L);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator((Interpolator)new AccelerateInterpolator());
        rotate3dAnimation.setAnimationListener((Animation.AnimationListener)new DisplayNextView(n));
        this.mContainer.startAnimation((Animation)rotate3dAnimation);
    }
    
    public static Intent getImageFileIntent(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(s)), "image/*");
        return intent;
    }
    
    public static Intent getVideoFileIntent(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(67108864);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(Uri.fromFile(new File(s)), "video/*");
        return intent;
    }
    
    public void back(final View view) {
        this.finish();
    }
    
    public void init() {
        this.unLockDateList = new ArrayList<String[]>();
        this.isSelectList = new ArrayList<String>();
        this.front_back_list_text = new ArrayList<String>();
        this.set_data_rl = (RelativeLayout)this.findViewById(2131361850);
        this.monthBtn = (Button)this.findViewById(2131361855);
        this.dayBtn = (Button)this.findViewById(2131361856);
        this.selectBtn = (Button)this.findViewById(2131361853);
        this.frontBackBtn = (Button)this.findViewById(2131361854);
        this.isTodayBtn = (Button)this.findViewById(2131361852);
        this.switchBrn = (Button)this.findViewById(2131361857);
        this.videoShowGrid = (GridView)this.findViewById(2131361859);
        this.videoshowListview = (ListView)this.findViewById(2131361860);
        this.progressBar = (ProgressBar)this.findViewById(2131361862);
        this.sorry_text = (TextView)this.findViewById(2131361861);
        (this.mContainer = (ViewGroup)this.findViewById(2131361858)).setPersistentDrawingCache(1);
        final String[] stringArray = this.getResources().getStringArray(2131230720);
        for (int i = 0; i < stringArray.length; ++i) {
            this.isSelectList.add(stringArray[i]);
        }
        final String[] stringArray2 = this.getResources().getStringArray(2131230721);
        for (int j = 0; j < stringArray2.length; ++j) {
            this.front_back_list_text.add(stringArray2[j]);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903058);
        this.init();
        this.UiHandler.sendEmptyMessage(-1);
        this.setTodayListener();
        new UpdataTh().start();
    }
    
    protected void onDestroy() {
        this.sendBroadcast(new Intent("android.intent.action.DISPLAY_STATUS_BAR"));
        super.onDestroy();
    }
    
    protected void onResume() {
        this.sendBroadcast(new Intent("android.intent.action.HIDE_STATUS_BAR"));
        this.date = Util.getTime();
        this.isTodayBtn.setText((CharSequence)(this.getResources().getString(2131165301) + "\n" + this.date[0] + "-" + (this.date[1] + 1) + "-" + this.date[2] + "\n" + "\n" + this.getResources().getString(2131165302)));
        super.onResume();
    }
    
    protected void onStop() {
        this.sendBroadcast(new Intent("android.intent.action.DISPLAY_STATUS_BAR"));
        super.onPause();
    }
    
    public String removeFirst(final String s) {
        String substring = s;
        if (s.startsWith("0")) {
            substring = s.substring(1);
        }
        return substring;
    }
    
    public void setButtonListen(final Button button, final ArrayList<String> list, final int n) {
        final String[] array = new String[list.size()];
        for (int i = 0; i < array.length; ++i) {
            switch (n) {
                default:
                    array[i] = list.get(i);
                    break;
                case 1:
                    array[i] = this.removeFirst(list.get(i)) + " " + this.getResources().getString(2131165296);
                    break;
                case 2:
                    array[i] = this.removeFirst(list.get(i)) + " " + this.getResources().getString(2131165297);
                    break;
                case 3:
                    array[i] = this.removeFirst(list.get(i)) + " " + this.getResources().getString(2131165298);
                    break;
            }
        }
        final AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder((Context)this);
        alertDialog$Builder.setIcon(2130837598);
        alertDialog$Builder.setItems((CharSequence[])array, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                switch (n) {
                    case 0:
                        VideoShowActivity.this.isSelect = n;
                        button.setText((CharSequence)list.get(n));
                        if (VideoShowActivity.this.isSelect == 2) {
                            if (!VideoShowActivity.this.frontBack.contains("jpg")) {
                                VideoShowActivity.this.frontBack = VideoShowActivity.this.frontBack.replace("mp4", "jpg");
                            }
                        }
                        else if (!VideoShowActivity.this.frontBack.contains("mp4")) {
                            VideoShowActivity.this.frontBack = VideoShowActivity.this.frontBack.replace("jpg", "mp4");
                        }
                        VideoShowActivity.this.isNewSearch = true;
                        break;
                    case 2:
                        VideoShowActivity.this.month = list.get(n);
                        VideoShowActivity.monthIsChance = true;
                        break;
                    case 3:
                        VideoShowActivity.this.day = list.get(n);
                        break;
                    case 4:
                        VideoShowActivity.this.frontBackSelect = n;
                        if (VideoShowActivity.this.isSelect == 2) {
                            if (n == 0) {
                                VideoShowActivity.this.frontBack = VideoShowActivity.this.front_back_list_data[n];
                            }
                            else if (n == 1) {
                                VideoShowActivity.this.frontBack = VideoShowActivity.this.front_back_list_data[3];
                            }
                            else if (n == 2) {
                                VideoShowActivity.this.frontBack = VideoShowActivity.this.front_back_list_data[4];
                            }
                        }
                        else {
                            VideoShowActivity.this.frontBack = VideoShowActivity.this.front_back_list_data[n];
                        }
                        button.setText((CharSequence)list.get(n));
                        break;
                }
                VideoShowActivity.this.UiHandler.sendEmptyMessage(1);
            }
        }).create();
        switch (n) {
            case 0:
                button.setText((CharSequence)this.isSelectList.get(this.isSelect));
                alertDialog$Builder.setTitle((CharSequence)this.getResources().getString(2131165291));
                break;
            case 2:
                if (" ".equals(this.month)) {
                    button.setText((CharSequence)(this.getResources().getString(2131165305) + " " + this.getResources().getString(2131165297)));
                }
                else {
                    button.setText((CharSequence)(this.removeFirst(this.month) + " " + this.getResources().getString(2131165297)));
                }
                alertDialog$Builder.setTitle((CharSequence)this.getResources().getString(2131165293));
                break;
            case 3:
                if (" ".equals(this.day)) {
                    button.setText((CharSequence)(this.getResources().getString(2131165305) + " " + this.getResources().getString(2131165298)));
                    alertDialog$Builder.setTitle((CharSequence)(this.getResources().getString(2131165305) + this.getResources().getString(2131165294)));
                    break;
                }
                button.setText((CharSequence)(this.removeFirst(this.day) + " " + this.getResources().getString(2131165298)));
                alertDialog$Builder.setTitle((CharSequence)(this.removeFirst(this.month) + this.getResources().getString(2131165294)));
                break;
            case 4:
                button.setText((CharSequence)this.front_back_list_text.get(this.frontBackSelect));
                alertDialog$Builder.setTitle((CharSequence)this.getResources().getString(2131165295));
                break;
        }
        button.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                if (array != null && array.length != 0) {
                    alertDialog$Builder.show();
                    return;
                }
                Toast.makeText((Context)VideoShowActivity.this, (CharSequence)VideoShowActivity.this.getResources().getString(2131165303), 1000).show();
            }
        });
    }
    
    public void setDayData() {
        this.dayList = new ArrayList<String>();
        for (int i = 0; i < this.unLockDateList.size(); ++i) {
            if (Util.getTime(this.unLockDateList.get(i)[1])[1].contains(this.month)) {
                this.dayList.add(Util.getTime(this.unLockDateList.get(i)[1])[2]);
            }
        }
        this.dayList = Util.removeDuplicateWithOrder(this.dayList);
        if (this.dayList == null) {
            this.dayList = new ArrayList<String>();
        }
        if (this.isNewSearch || "".equals(this.day) || this.day == null) {
            this.day = " ";
        }
        else if (VideoShowActivity.monthIsChance) {
            this.day = this.dayList.get(this.dayList.size() - 1);
        }
        this.setVideoByDay();
    }
    
    public void setGridView(final ArrayList<String[]> list) {
        if (list == null || list.size() == 0) {
            this.sorry_text.setText((CharSequence)this.getResources().getString(2131165300));
            this.sorry_text.setVisibility(0);
        }
        else {
            this.sorry_text.setVisibility(8);
        }
        this.gridViewAdpater = new GridViewAdpater((Context)this, list, this.isSelect);
        this.videoShowGrid.setAdapter((ListAdapter)this.gridViewAdpater);
        this.gridViewAdpater.notifyDataSetInvalidated();
        this.videoShowGrid.setOnItemClickListener((AdapterView.OnItemClickListener)new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (VideoShowActivity.this.isSelect == 2) {
                    VideoShowActivity.this.startActivity(VideoShowActivity.getImageFileIntent(((String[])list.get(n))[0]));
                    return;
                }
                VideoShowActivity.this.startActivity(VideoShowActivity.getVideoFileIntent(((String[])list.get(n))[0]));
            }
        });
    }
    
    public void setListView(final ArrayList<String[]> list) {
        if (list == null || list.size() == 0) {
            this.sorry_text.setText((CharSequence)this.getResources().getString(2131165300));
            this.sorry_text.setVisibility(0);
        }
        else {
            this.sorry_text.setVisibility(8);
        }
        this.listViewAdpter = new ListViewAdpter((Context)this, list, this.isSelect);
        this.videoshowListview.setAdapter((ListAdapter)this.listViewAdpter);
        this.listViewAdpter.notifyDataSetInvalidated();
        this.videoshowListview.setOnItemClickListener((AdapterView.OnItemClickListener)new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (VideoShowActivity.this.isSelect == 2) {
                    VideoShowActivity.this.startActivity(VideoShowActivity.getImageFileIntent(((String[])list.get(n))[0]));
                    return;
                }
                VideoShowActivity.this.startActivity(VideoShowActivity.getVideoFileIntent(((String[])list.get(n))[0]));
            }
        });
    }
    
    public void setMonthData() {
        this.monthList = new ArrayList<String>();
        for (int i = 0; i < this.unLockDateList.size(); ++i) {
            this.monthList.add(Util.getTime(this.unLockDateList.get(i)[1])[1]);
        }
        this.monthList = Util.removeDuplicateWithOrder(this.monthList);
        if (this.monthList == null) {
            this.monthList = new ArrayList<String>();
        }
        if (this.isNewSearch || "".equals(this.month) || this.month == null) {
            this.month = " ";
        }
        this.setDayData();
    }
    
    public void setTodayListener() {
        this.isTodayBtn.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                VideoShowActivity.this.month = VideoShowActivity.this.date[1] + 1 + "";
                VideoShowActivity.this.day = VideoShowActivity.this.date[2] + "";
                VideoShowActivity.this.isSelect = 0;
                VideoShowActivity.this.frontBackSelect = 0;
                VideoShowActivity.this.UiHandler.sendEmptyMessage(1);
            }
        });
        this.switchBrn.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
            public void onClick(final View view) {
                VideoShowActivity.this.viewNum++;
                switch (VideoShowActivity.this.viewNum % 2) {
                    case 0:
                        VideoShowActivity.this.applyRotation(0, 0.0f, 90.0f);
                        VideoShowActivity.this.switchBrn.setBackgroundResource(2130837628);
                        VideoShowActivity.this.UiHandler.sendEmptyMessage(3);
                        break;
                    case 1:
                        VideoShowActivity.this.applyRotation(1, 0.0f, 90.0f);
                        VideoShowActivity.this.switchBrn.setBackgroundResource(2130837627);
                        VideoShowActivity.this.UiHandler.sendEmptyMessage(4);
                        break;
                }
                VideoShowActivity.this.viewNum %= 2;
            }
        });
    }
    
    public void setVideoByDay() {
        if (this.isNewSearch || "".equals(this.frontBack) || this.frontBack == null) {
            this.frontBack = this.front_back_list_data[0];
            this.frontBackSelect = 0;
        }
        this.list = new ArrayList<String[]>();
        for (int i = 0; i < this.unLockDateList.size(); ++i) {
            final String s = Util.getTime(this.unLockDateList.get(i)[1])[1];
            final String s2 = Util.getTime(this.unLockDateList.get(i)[1])[2];
            final String string = "_" + Util.getTime(this.unLockDateList.get(i)[1])[6];
            if (s.contains(this.month) && s2.contains(this.day) && string.contains(this.frontBack)) {
                this.list.add(new String[] { this.unLockDateList.get(i)[0], Util.getAllDate(this.unLockDateList.get(i)[1]) });
            }
        }
        this.UiHandler.sendEmptyMessage(0);
        this.UiHandler.sendEmptyMessage(2);
    }
    
    public void setYearData() {
        this.yearList = new ArrayList<String>();
        for (int i = 0; i < this.unLockDateList.size(); ++i) {
            this.yearList.add(Util.getTime(this.unLockDateList.get(i)[1])[0]);
        }
        this.yearList = Util.removeDuplicateWithOrder(this.yearList);
        if (this.yearList == null) {
            this.yearList = new ArrayList<String>();
        }
        this.setMonthData();
    }
    
    public void set_y_m_d_data() {
        this.unLockDateList = FileStorage.getDateByContentResolver(this.getContentResolver(), this.isSelect);
        if (this.unLockDateList == null) {
            this.unLockDateList = new ArrayList<String[]>();
        }
        this.setYearData();
    }
    
    private final class DisplayNextView implements Animation.AnimationListener
    {
        private final int mPosition;
        
        private DisplayNextView(final int mPosition) {
            this.mPosition = mPosition;
        }
        
        public void onAnimationEnd(final Animation animation) {
            VideoShowActivity.this.mContainer.post((Runnable)new SwapViews(this.mPosition));
        }
        
        public void onAnimationRepeat(final Animation animation) {
        }
        
        public void onAnimationStart(final Animation animation) {
        }
    }
    
    private final class SwapViews implements Runnable
    {
        private final int mPosition;
        
        public SwapViews(final int mPosition) {
            this.mPosition = mPosition;
        }
        
        @Override
        public void run() {
            final float n = VideoShowActivity.this.mContainer.getWidth() / 2.0f;
            final float n2 = VideoShowActivity.this.mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotate3dAnimation;
            if (this.mPosition == 0) {
                rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, n, n2, 310.0f, false);
            }
            else {
                rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, n, n2, 310.0f, false);
            }
            rotate3dAnimation.setDuration(500L);
            rotate3dAnimation.setFillAfter(true);
            rotate3dAnimation.setInterpolator((Interpolator)new DecelerateInterpolator());
            VideoShowActivity.this.mContainer.startAnimation((Animation)rotate3dAnimation);
        }
    }
    
    class UpdataTh extends Thread
    {
        @Override
        public void run() {
            Looper.prepare();
            VideoShowActivity.this.set_y_m_d_data();
            Looper.loop();
        }
    }
}
