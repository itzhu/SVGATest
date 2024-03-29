package me.test.svga;

import android.app.Application;
import android.os.Environment;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import me.test.svga.loader.SVGACacheManager;

/**
 * Created by itzhu on 2019/8/24.
 */
public class TApplication extends Application {

    private static TApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        //代码中初始化
        Logger.addLogAdapter(new AndroidLogAdapter());

        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(false)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        try {
            String cachePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+".file_svga_cache";
            Logger.d("svga 文件缓存路径cachePath:" + cachePath);
            SVGACacheManager.getInstance().init(this, cachePath);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static TApplication getInstance() {
        return application;
    }
}
