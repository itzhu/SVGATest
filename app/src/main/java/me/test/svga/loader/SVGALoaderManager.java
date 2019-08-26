package me.test.svga.loader;

import android.content.Context;
import android.net.http.HttpResponseCache;

import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itzhu on 2019/8/24.
 * 加载svga文件
 */
public class SVGALoaderManager {

    private SVGAParser mSVGAParser;
    private Map<String, LoadRequest> mRequestDataMap;

    private static final SVGALoaderManager ourInstance = new SVGALoaderManager();

    public static SVGALoaderManager getInstance() {
        return ourInstance;
    }

    private SVGALoaderManager() {
    }

    /**
     * @param context
     * @param cacheDirPath 缓存文件夹路径
     * @param maxCacheSize 缓存文件夹最大大小
     * @throws IOException
     */
    public void init(Context context, String cacheDirPath, long maxCacheSize) throws IOException {
        mSVGAParser = new SVGAParser(context);
        //配置文件缓存文件夹
        File cacheDir = new File(cacheDirPath);
        HttpResponseCache.install(cacheDir, maxCacheSize);
        mRequestDataMap = new HashMap<>(50);
    }

    public void destroy() {
        mRequestDataMap.clear();
    }

    public synchronized boolean addRequest(LoadRequest request) {
        URL url = request.getUrl();
        if (url == null) {
            return false;
        }
        if (!request.createTag()) {
            return false;
        }
        getRequestDataMap().put(request.getKey(), request);
        checkRequestDataMap();
        mSVGAParser.decodeFromURL(request.getUrl(), request.createSvgaParserCompletion());
        return true;
    }


    public synchronized void dealResult(LoadResult result) {
        String key = result.getKey();
        LoadRequest request = getRequestDataMap().get(key);
        if (request == null) {
            return;
        }
        SVGATarget target = request.getSvgaTarget();
        if (target != null
                && result.getTag().equals(target.getTag())) {
            target.loadFinish(result);
        }
    }

    /**
     * todo
     * 如果requestMap数据超过了50条，就要检测数据是否有无用数据，避免垃圾数据过多导致内存泄漏
     */
    private void checkRequestDataMap() {

    }

    public Map<String,
            LoadRequest> getRequestDataMap() {
        return mRequestDataMap;
    }

    public void clearSVGAAnimation(SVGAImageView svgaImageView) {
        String tag = String.valueOf(svgaImageView.hashCode());
        getRequestDataMap().remove(tag);
        svgaImageView.stopAnimation(true);
    }
}
