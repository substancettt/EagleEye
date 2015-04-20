package com.wellshang.eagleeye.storage;

import android.net.*;
import android.util.*;
import java.io.*;
import android.provider.*;
import android.content.*;
import android.os.storage.*;
import android.database.*;
import android.location.*;
import android.os.*;
import android.graphics.*;
import android.media.*;
import java.util.*;
import com.wellshang.eagleeye.adapter.*;

public class FileStorage
{
    private static boolean CODE_DEBUG = false;
    public static final String DIRECTORY = "/mnt/extsd/DCIM";
    private static boolean[] FILE_LOCK_FLAG;
    private static int FILE_LOCK_MAX = 0;
    public static final long FULL_STORAGE_THRESHOLD = 104857600L;
    public static final String LOCK_DIR = "/mnt/extsd/DCIM/LOCK";
    public static final long LOW_STORAGE_THRESHOLD = 209715200L;
    public static final String PHTOT_DIR = "/mnt/extsd/DCIM/PIC";
    public static final long PICTURE_SIZE = 1500000L;
    public static final long PREPARING = -2L;
    private static final String TAG = "FileStorage";
    public static final int THUMB_SIZE = 100;
    public static final long UNAVAILABLE = -1L;
    public static final long UNKNOWN_SIZE = -3L;
    public static final String VIDEO_DIR = "/mnt/extsd/DCIM/VID";
    private static Bitmap mImageBitmap;
    public static long[] mImageDate;
    private static String[] mImageFilename;
    private static String[] mImageTitle;
    private static boolean mThumbNail;
    private static Bitmap mVideoBitmap;
    public static long[] mVideoDate;
    private static String[] mVideoFilename;
    private static String[] mVideoTitle;
    
    static {
        FileStorage.CODE_DEBUG = false;
        FileStorage.mThumbNail = false;
        FileStorage.mImageBitmap = null;
        FileStorage.mVideoBitmap = null;
        FileStorage.mImageDate = new long[4];
        FileStorage.mVideoDate = new long[4];
        FileStorage.mImageTitle = new String[4];
        FileStorage.mVideoTitle = new String[4];
        FileStorage.mImageFilename = new String[4];
        FileStorage.mVideoFilename = new String[4];
        FileStorage.FILE_LOCK_MAX = 20;
        FileStorage.FILE_LOCK_FLAG = new boolean[] { false, false, false, false };
    }
    
    public static Uri AddImageToMediaStore(final ContentResolver contentResolver, final long n, final int n2) {
        final ContentValues contentValues = new ContentValues(7);
        contentValues.put("title", FileStorage.mImageTitle[n2]);
        contentValues.put("_display_name", FileStorage.mImageTitle[n2] + ".jpg");
        contentValues.put("datetaken", FileStorage.mImageDate[n2]);
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("_data", FileStorage.mImageFilename[n2]);
        Label_0152: {
            if (n <= 0L) {
                break Label_0152;
            }
            contentValues.put("orientation", n);
            while (true) {
                final Uri uri = null;
                while (true) {
                    try {
                    	final Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        if (FileStorage.CODE_DEBUG) {
                            Log.d("FileStorage", "AddImageToMediaStore uri :" + insert);
                        }
                        return insert;
                    }
                    catch (Throwable t) {
                        Log.d("FileStorage", "Failed to write MediaStore" + t);
                        final Uri insert = uri;
                        continue;
                    }
                }
            }
        }
        return null;
    }
    
    public static Uri AddVideoToMediaStore(final ContentResolver contentResolver, final long n, final int n2) {
        if (FileStorage.FILE_LOCK_FLAG[n2]) {
            final File file = new File(FileStorage.mVideoFilename[n2]);
            FileStorage.mVideoFilename[n2] = FileStorage.mVideoFilename[n2].replace("/mnt/extsd/DCIM/VID", "/mnt/extsd/DCIM/LOCK");
            file.renameTo(new File(FileStorage.mVideoFilename[n2]));
            FileStorage.FILE_LOCK_FLAG[n2] = false;
        }
        final ContentValues contentValues = new ContentValues(7);
        contentValues.put("title", FileStorage.mVideoTitle[n2]);
        contentValues.put("_display_name", FileStorage.mVideoTitle[n2] + ".mp4");
        contentValues.put("datetaken", FileStorage.mVideoDate[n2]);
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("_data", FileStorage.mVideoFilename[n2]);
        Label_0215: {
            if (n <= 0L) {
                break Label_0215;
            }
            contentValues.put("duration", n);
            while (true) {
                final File file = null;
                while (true) {
                    try {
                        final Object insert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                        if (FileStorage.CODE_DEBUG) {
                            Log.d("FileStorage", "AddVideoToMediaStore uri :" + insert);
                        }
                        return (Uri)insert;
                    }
                    catch (Throwable t) {
                        Log.d("FileStorage", "Failed to write MediaStore" + t);
                        final Object insert = file;
                        continue;
                    }
                }
            }
        }
        return null;
    }
    
    public static boolean CheckExternalStorage(final Context context) {
//        final String volumeState = ((StorageManager)context.getSystemService("storage")).getVolumeState("/mnt/extsd");
      final String volumeState = "Hehe";
    	Log.d("FileStorage", "state = " + volumeState);
        if ("checking".equals(volumeState) || !"mounted".equals(volumeState)) {
            return false;
        }
        final File file = new File("/mnt/extsd/DCIM");
        if (!file.exists()) {
            Log.d("FileStorage", "DIRECTORY [ /mnt/extsd/DCIM ] is not exist");
            if (!file.mkdirs()) {
                Log.d("FileStorage", "Create Dir [ /mnt/extsd/DCIM ] is fail");
                return false;
            }
        }
        if (!file.isDirectory() || !file.canWrite()) {
            Log.d("FileStorage", "Create Dir [ /mnt/extsd/DCIM ] can not write");
            return false;
        }
        final File file2 = new File("/mnt/extsd/DCIM/PIC");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        final File file3 = new File("/mnt/extsd/DCIM/VID");
        if (!file3.exists()) {
            file3.mkdirs();
        }
        final File file4 = new File("/mnt/extsd/DCIM/LOCK");
        if (!file4.exists()) {
            file4.mkdirs();
        }
        return true;
    }
    
    public static boolean DelFirstVideoFile(final ContentResolver contentResolver) {
        Cursor query;
        try {
            query = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
            if (query == null) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "DelFirstVideoFile cursor Count :" + query.getCount());
        }
        final boolean moveToFirst = query.moveToFirst();
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "DelFirstVideoFile ret :" + moveToFirst);
        }
        if (!moveToFirst) {
            query.close();
            return false;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "getCount :" + query.getCount());
        }
        final String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Deletet fpath :" + string);
        }
        final File file = new File(string);
        if (file.isFile() && file.exists()) {
            if (file.delete()) {
                contentResolver.delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data =?", new String[] { string });
                if (FileStorage.CODE_DEBUG) {
                    Log.d("FileStorage", "Deletet file [ " + string + " ] succeed");
                }
                return true;
            }
            Log.e("FileStorage", "Deletet file [ " + string + " ] failed");
        }
        else {
            Log.e("FileStorage", "File [ " + string + " ] is not exist or is not file");
        }
        return false;
    }
    
    public static boolean DelLastVideoFile(final ContentResolver contentResolver, final int n) {
        Cursor query;
        try {
            query = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
            if (query == null) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "DelFirstVideoFile cursor Count :" + query.getCount());
        }
        final boolean moveToLast = query.moveToLast();
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "DelFirstVideoFile ret :" + moveToLast);
        }
        if (!moveToLast) {
            query.close();
            return false;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "getCount :" + query.getCount());
        }
        if (n != 0) {
            final String string = query.getString(query.getColumnIndex("_data"));
            query.close();
            if (FileStorage.CODE_DEBUG) {
                Log.d("FileStorage", "Deletet fpath :" + string);
            }
            final File file = new File(string);
            if (file.isFile() && file.exists()) {
                if (file.delete()) {
                    contentResolver.delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data =?", new String[] { string });
                    if (FileStorage.CODE_DEBUG) {
                        Log.d("FileStorage", "Deletet file [ " + string + " ] succeed");
                    }
                    return true;
                }
                Log.e("FileStorage", "Deletet file [ " + string + " ] failed");
            }
            else {
                Log.e("FileStorage", "File [ " + string + " ] is not exist or is not file");
            }
        }
        else {
            final String string2 = query.getString(query.getColumnIndex("_data"));
            if (FileStorage.CODE_DEBUG) {
                Log.d("FileStorage", "Deletet fpath :" + string2);
            }
            final File file2 = new File(string2);
            if (file2.isFile() && file2.exists()) {
                if (file2.delete()) {
                    contentResolver.delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data =?", new String[] { string2 });
                    if (FileStorage.CODE_DEBUG) {
                        Log.d("FileStorage", "Deletet file [ " + string2 + " ] succeed");
                    }
                }
                else {
                    Log.e("FileStorage", "Deletet file [ " + string2 + " ] failed");
                }
            }
            else {
                Log.e("FileStorage", "File [ " + string2 + " ] is not exist or is not file");
            }
            if (!query.moveToPrevious()) {
                query.close();
                return true;
            }
            final String string3 = query.getString(query.getColumnIndex("_data"));
            query.close();
            if (FileStorage.CODE_DEBUG) {
                Log.d("FileStorage", "Deletet fpath :" + string3);
            }
            final File file3 = new File(string3);
            if (file3.isFile() && file3.exists()) {
                if (file3.delete()) {
                    contentResolver.delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data =?", new String[] { string3 });
                    if (FileStorage.CODE_DEBUG) {
                        Log.d("FileStorage", "Deletet file [ " + string2 + " ] succeed");
                    }
                    return true;
                }
                Log.e("FileStorage", "Deletet file [ " + string2 + " ] failed");
            }
            else {
                Log.e("FileStorage", "File [ " + string2 + " ] is not exist or is not file");
            }
        }
        return false;
    }
    
    public static String GenerateImageFilename(final String s, final long n, final Location location, final int n2) {
        if (n2 == 0) {
            FileStorage.mImageTitle[n2] = s + "_0";
        }
        else if (n2 == 1) {
            FileStorage.mImageTitle[n2] = s + "_1";
        }
        else {
            if (n2 != 2) {
                return null;
            }
            FileStorage.mImageTitle[n2] = s + "_2";
        }
        FileStorage.mImageDate[n2] = n;
        FileStorage.mImageFilename[n2] = "/mnt/extsd/DCIM/PIC/" + FileStorage.mImageTitle[n2] + ".jpg";
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "New image filename: " + FileStorage.mImageFilename[n2]);
        }
        return FileStorage.mImageFilename[n2];
    }
    
    public static String GenerateVideoFilename(final String s, final long n, final Location location, final int n2) {
        if (n2 == 0) {
            FileStorage.mVideoTitle[n2] = s + "_0";
        }
        else if (n2 == 1) {
            FileStorage.mVideoTitle[n2] = s + "_1";
        }
        else {
            if (n2 != 2) {
                return null;
            }
            FileStorage.mVideoTitle[n2] = s + "_2";
        }
        FileStorage.mVideoDate[n2] = n;
        FileStorage.mVideoFilename[n2] = "/mnt/extsd/DCIM/VID/" + FileStorage.mVideoTitle[n2] + ".mp4";
        Log.d("FileStorage", "New video filename: " + FileStorage.mVideoFilename[n2]);
        return FileStorage.mVideoFilename[n2];
    }
    
    public static long GetAvailableSpace() {
        try {
            final StatFs statFs = new StatFs("/mnt/extsd/DCIM");
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        }
        catch (Exception ex) {
            Log.d("FileStorage", "Fail to access external storage", (Throwable)ex);
            return -3L;
        }
    }
    
    public static Bitmap GetImageThumbNail() {
        int i = 0;
        if (FileStorage.mImageBitmap == null) {
            if (FileStorage.mImageBitmap != null && !FileStorage.mImageBitmap.isRecycled()) {
                FileStorage.mImageBitmap.recycle();
            }
            final BitmapFactory.Options bitmapFactory$Options = new BitmapFactory.Options();
            bitmapFactory$Options.outWidth = 100;
            bitmapFactory$Options.outHeight = 100;
            while (i < FileStorage.mImageFilename.length) {
                if (FileStorage.mImageFilename[i] != null) {
                    FileStorage.mImageBitmap = BitmapFactory.decodeFile(FileStorage.mImageFilename[i], bitmapFactory$Options);
                }
                ++i;
            }
        }
        return FileStorage.mImageBitmap;
    }
    
    public static Uri GetLastImageFile(final ContentResolver contentResolver) {
        final Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/PIC%'", (String[])null, "_data");
        if (query == null) {
            return null;
        }
        if (!query.moveToLast()) {
            query.close();
            return null;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Image getCount :" + query.getCount());
        }
        final int columnIndex = query.getColumnIndex("_id");
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Image columnIndex :" + columnIndex);
        }
        final String string = query.getString(columnIndex);
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Image count :" + string);
        }
        final String string2 = query.getString(query.getColumnIndex("_data"));
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Image fpath :" + string2);
        }
        query.close();
        final Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, string);
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Last Image File Uri :" + withAppendedPath);
        }
        if (FileStorage.mThumbNail) {
            if (FileStorage.mImageBitmap != null && !FileStorage.mImageBitmap.isRecycled()) {
                FileStorage.mImageBitmap.recycle();
            }
            final BitmapFactory.Options bitmapFactory$Options = new BitmapFactory.Options();
            bitmapFactory$Options.outWidth = 100;
            bitmapFactory$Options.outHeight = 100;
            FileStorage.mImageBitmap = BitmapFactory.decodeFile(string2, bitmapFactory$Options);
        }
        return withAppendedPath;
    }
    
    public static Uri GetLastVideoFile(final ContentResolver contentResolver) {
        final Cursor query = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
        if (query == null) {
            return null;
        }
        Log.d("FileStorage", "GetLastVideoFile cursor Count :" + query.getCount());
        final boolean moveToLast = query.moveToLast();
        Log.d("FileStorage", "GetLastVideoFile ret :" + moveToLast);
        if (!moveToLast) {
            query.close();
            return null;
        }
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Video getCount :" + query.getCount());
        }
        final int columnIndex = query.getColumnIndex("_id");
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Video columnIndex :" + columnIndex);
        }
        final String string = query.getString(columnIndex);
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Video count :" + string);
        }
        final String string2 = query.getString(query.getColumnIndex("_data"));
        Log.d("FileStorage", "Video fpath :" + string2);
        query.close();
        final Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string);
        if (FileStorage.CODE_DEBUG) {
            Log.d("FileStorage", "Last Video File Uri :" + withAppendedPath);
        }
        if (FileStorage.mThumbNail) {
            if (FileStorage.mVideoBitmap != null && !FileStorage.mVideoBitmap.isRecycled()) {
                FileStorage.mVideoBitmap.recycle();
            }
            final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(string2);
            FileStorage.mVideoBitmap = mediaMetadataRetriever.getFrameAtTime(-1L);
            mediaMetadataRetriever.release();
        }
        return withAppendedPath;
    }
    
    public static boolean GetVideoFileSpace(final long n) {
        long n2 = 0L;
        final File[] listFiles = new File("/mnt/extsd/DCIM/VID").listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (!listFiles[i].isDirectory()) {
                    final long n3 = n2 + listFiles[i].length();
                    Log.i("FileStorage", "size " + n3);
                    n2 = n3;
                    if (n3 > n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static Bitmap GetVideoThumbNail() {
        int i = 0;
        if (FileStorage.mVideoBitmap == null) {
            if (FileStorage.mVideoBitmap != null && !FileStorage.mVideoBitmap.isRecycled()) {
                FileStorage.mVideoBitmap.recycle();
            }
            final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            while (i < FileStorage.mImageFilename.length) {
                if (FileStorage.mVideoFilename[i] != null) {
                    mediaMetadataRetriever.setDataSource(FileStorage.mVideoFilename[i]);
                    FileStorage.mVideoBitmap = mediaMetadataRetriever.getFrameAtTime(-1L);
                    mediaMetadataRetriever.release();
                }
                ++i;
            }
        }
        return FileStorage.mVideoBitmap;
    }
    
    public static void SetFileLock(final boolean b) {
        FileStorage.FILE_LOCK_FLAG[0] = b;
        FileStorage.FILE_LOCK_FLAG[1] = b;
        FileStorage.FILE_LOCK_FLAG[2] = b;
        FileStorage.FILE_LOCK_FLAG[3] = b;
    }
    
    public static void SetThumbNailStatus(final boolean mThumbNail) {
        FileStorage.mThumbNail = mThumbNail;
    }
    
    public static boolean StorageDiskIsFull(final long n) {
        return n < 209715200L;
    }
    
    public static ArrayList<String[]> getDateByContentResolver(final ContentResolver contentResolver, final int n) {
        final ArrayList<String[]> list = new ArrayList<String[]>();
        Cursor cursor = null;
        if (n == 0) {
            cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
        }
        else if (n == 1) {
            cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/LOCK%'", (String[])null, "_data");
        }
        else if (n == 2) {
            cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/PIC%'", (String[])null, "_data");
        }
        if (cursor == null) {
            return null;
        }
        if (!cursor.moveToLast()) {
            cursor.close();
            return null;
        }
        for (int i = 0; i < cursor.getCount(); ++i) {
            cursor.moveToPosition(i);
            if (n == 2) {
                list.add(new String[] { cursor.getString(1), cursor.getString(3) });
            }
            else {
                list.add(new String[] { cursor.getString(1), cursor.getString(2) });
            }
        }
        cursor.close();
        return list;
    }
    
    public static Bitmap getVideoBitMapByContentResolver(final ContentResolver contentResolver, final int n, final String dataSource) {
        Cursor cursor = null;
        if (n == 0) {
            cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/VID%'", (String[])null, "_data");
        }
        else if (n == 1) {
            cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/LOCK%'", (String[])null, "_data");
        }
        else if (n == 2) {
            cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like '%/mnt/extsd/DCIM/PIC%'", (String[])null, "_data");
        }
        if (cursor == null) {
            return null;
        }
        if (!cursor.moveToLast()) {
            cursor.close();
            return null;
        }
        final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(dataSource);
        final Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(-1L);
        Util.smallBitmap(frameAtTime);
        mediaMetadataRetriever.release();
        cursor.close();
        return frameAtTime;
    }
}
