package me.test.svga;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by itzhu on 2019/8/24.
 */
public class TApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //代码中初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
