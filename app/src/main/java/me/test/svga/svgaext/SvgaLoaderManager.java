package me.test.svga.svgaext;

import android.content.Context;
import android.net.http.HttpResponseCache;

import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by itzhu on 2019/8/24.
 * 加载svga文件
 */
public class SvgaLoaderManager {

    private SVGAParser mSvgaParser;
    private HashMap<String, LoadRequest> requestDataMap;

    private static final SvgaLoaderManager ourInstance = new SvgaLoaderManager();

    public static SvgaLoaderManager getInstance() {
        return ourInstance;
    }

    private SvgaLoaderManager() {
    }

    /**
     * @param context
     * @param cacheDirPath 缓存文件夹路径
     * @param maxCacheSize 缓存文件夹最大大小
     * @throws IOException
     */
    public void init(Context context, String cacheDirPath, long maxCacheSize) throws IOException {
        mSvgaParser = new SVGAParser(context);
        //配置文件缓存文件夹
        File cacheDir = new File(cacheDirPath);
        HttpResponseCache.install(cacheDir, maxCacheSize);
        requestDataMap = new HashMap<>(50);
    }

    public void destroy() {
        requestDataMap.clear();
    }

    public void addRequest(LoadRequest request) {
        getRequestDataMap().put(request.getKey(), request);
        checkRequestDataMap();
        // TODO: 2019/8/24 需要检查要加载的视图是否需要设置url为null
        mSvgaParser.decodeFromURL(request.getUrl(), request.createSvgaParserCompletion());
    }


    public synchronized void dealResult(LoadRequestResult requestResult) {
        String key = requestResult.getSvgaViewKey();
        LoadRequest request = getRequestDataMap().get(key);
        if (request != null) {


        }
    }

    /**
     * todo
     * 如果requestMap数据超过了50条，就要检测数据是否有无用数据，避免垃圾数据过多导致内存泄漏
     */
    private void checkRequestDataMap() {

    }

    public HashMap<String, LoadRequest> getRequestDataMap() {
        return requestDataMap;
    }

    private void removeLoad(SVGAImageView svgaImageView) {
        String tag = String.valueOf(svgaImageView.hashCode());
        getRequestDataMap().remove(tag);
    }
}
