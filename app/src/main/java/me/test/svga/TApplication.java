package me.test.svga;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import me.test.svga.loader.SVGALoaderManager;

/**
 * Created by itzhu on 2019/8/24.
 */
public class TApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //代码中初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        try {
            String cachePath = getExternalCacheDir().getAbsolutePath();
            Logger.d("svga 文件缓存路径cachePath:" + cachePath);
            SVGALoaderManager.getInstance().init(this, cachePath, 1024 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
