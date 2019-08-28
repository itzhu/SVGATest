package me.test.svga.loader;

import android.util.Log;

import com.downloader.Error;
import com.google.gson.Gson;

/**
 * Created by itzhu on 2019/8/26.
 */
public class SL {


    private static final Gson gson = new Gson();

    public static void d(String message){
        Log.d("SVGA LOAD",message);
    }

    public static void d(String msg, Object obj) {
        d(msg+gson.toJson(obj));
    }
}
