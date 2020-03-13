package takjxh.android.com.commlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class ShareUtils {

    public static final String NAME = "config";

    public static void putString(Context mContext,String key,String value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context mContext,String key,String defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context mContext,String key,int value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(Context mContext,String key,int defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putBoolean(Context mContext,String key,boolean value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context mContext,String key,boolean defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void deleteShare(Context mContext, String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static void deleteAll(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    public static void putUri(Context mContext,String key,Uri value){
        SharedPreferences sp = mContext.getSharedPreferences("NAME",Context.MODE_PRIVATE);
        sp.edit().putString(key,value.toString()).commit();
    }

    public static Uri getUri(Context mContext,String key,Uri defValue){
      //  L.i("getUrigetUri: 11");
        SharedPreferences sp = mContext.getSharedPreferences("NAME",Context.MODE_PRIVATE);
    //    L.i("getUrigetUri: 1221");
        String defValueString = "";
        String str = sp.getString(key, defValueString);
   //     L.i("getUrigetUri:  " + str);
        Uri uri = Uri.parse(str);
  //      L.i("getUrigetUrigetUrigetUri:  " + str);
        return uri;
    }


}
