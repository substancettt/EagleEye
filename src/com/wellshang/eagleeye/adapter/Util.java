package com.wellshang.eagleeye.adapter;

import java.util.*;
import android.graphics.*;

public class Util
{
    private static int computeInitialSampleSize(final BitmapFactory.Options bitmapFactory$Options, final int n, final int n2) {
        final double n3 = bitmapFactory$Options.outWidth;
        final double n4 = bitmapFactory$Options.outHeight;
        int n5;
        if (n2 == -1) {
            n5 = 1;
        }
        else {
            n5 = (int)Math.ceil(Math.sqrt(n3 * n4 / n2));
        }
        int n6;
        if (n == -1) {
            n6 = 128;
        }
        else {
            n6 = (int)Math.min(Math.floor(n3 / n), Math.floor(n4 / n));
        }
        if (n6 >= n5) {
            if (n2 == -1 && n == -1) {
                return 1;
            }
            if (n != -1) {
                return n6;
            }
        }
        return n5;
    }
    
    public static int computeSampleSize(final BitmapFactory.Options bitmapFactory$Options, int n, int n2) {
        final int computeInitialSampleSize = computeInitialSampleSize(bitmapFactory$Options, n, n2);
        if (computeInitialSampleSize <= 8) {
            n = 1;
            while (true) {
                n2 = n;
                if (n >= computeInitialSampleSize) {
                    break;
                }
                n <<= 1;
            }
        }
        else {
            n2 = (computeInitialSampleSize + 7) / 8 * 8;
        }
        return n2;
    }
    
    public static String getAllDate(String s) {
        final String[] split = s.split("_");
        final String substring = split[1].substring(0, 4);
        final String substring2 = split[1].substring(4, 6);
        final String substring3 = split[1].substring(6);
        s = split[2].substring(0, 2);
        final String substring4 = split[2].substring(2, 4);
        final String substring5 = split[2].substring(4);
        final String string = substring + "\u5e74" + substring2 + "\u6708" + substring3 + "\u65e5";
        s = s + ":" + substring4 + ":" + substring5;
        return string + s;
    }
    
    public static Bitmap getBitmapFromFile(final String s, final int n, final int n2) {
        BitmapFactory.Options bitmapFactory$Options2;
        final BitmapFactory.Options bitmapFactory$Options = bitmapFactory$Options2 = null;
        if (n > 0) {
            bitmapFactory$Options2 = bitmapFactory$Options;
            if (n2 > 0) {
                bitmapFactory$Options2 = new BitmapFactory.Options();
                bitmapFactory$Options2.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(s, bitmapFactory$Options2);
                bitmapFactory$Options2.inSampleSize = computeSampleSize(bitmapFactory$Options2, Math.min(n, n2), n * n2);
                bitmapFactory$Options2.inJustDecodeBounds = false;
                bitmapFactory$Options2.inInputShareable = true;
                bitmapFactory$Options2.inPurgeable = true;
            }
        }
        try {
            return BitmapFactory.decodeFile(s, bitmapFactory$Options2);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
            return null;
        }
    }
    
    public static int[] getTime() {
        final Calendar instance = Calendar.getInstance();
        return new int[] { instance.get(1), instance.get(2), instance.get(5), instance.get(11), instance.get(12), instance.get(13) };
    }
    
    public static String[] getTime(final String s) {
        final String[] split = s.split("_");
        if (split.length == 4) {
            return new String[] { split[1].substring(0, 4) + " ", split[1].substring(4, 6) + " ", split[1].substring(6) + " ", split[2].substring(0, 2) + " ", split[2].substring(2, 4) + " ", split[2].substring(4) + " ", split[3] + "_" };
        }
        return null;
    }
    
    public static ArrayList<String> removeDuplicateWithOrder(final List<String> list) {
        final HashSet<String> set = new HashSet<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final String s : list) {
            if (set.add(s)) {
                list2.add(s);
            }
        }
        return list2;
    }
    
    public static Bitmap smallBitmap(final Bitmap bitmap) {
        if (bitmap != null) {
            final Matrix matrix = new Matrix();
            matrix.postScale(0.5f, 0.5f);
            return Bitmap.createBitmap(bitmap, 0, 0, 230, 150, matrix, true);
        }
        return null;
    }
}
