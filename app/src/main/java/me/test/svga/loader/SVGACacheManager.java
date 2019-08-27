package me.test.svga.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.LruCache;
import android.view.LayoutInflater;

import com.downloader.PRDownloader;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.test.svga.TApplication;
import me.test.svga.svgaloader.SVGAExtView;
import me.test.svga.svgaloader.Target;

/**
 * Created by itzhu on 2019/8/24.
 * 加载svga文件
 */
public class SVGACacheManager {

    private SVGAParser mSVGAParser;
    private String cacheDirPath;
    /**
     * 内存缓存
     */
    private Map<String, LruCache<String, SVGAVideoEntity>> lruCacheMap;
    private List<Target<SVGAExtView>> targetList = new ArrayList<>();

    private static final SVGACacheManager ourInstance = new SVGACacheManager();

    public static SVGACacheManager getInstance() {
        return ourInstance;
    }

    private SVGACacheManager() {
    }

    /**
     * @param context
     * @param cacheDirPath 缓存文件夹路径
     * @throws IOException
     */
    public void init(Context context, String cacheDirPath) throws IOException {
        this.mSVGAParser = new SVGAParser(context);
        //配置文件缓存文件夹
        this.cacheDirPath = cacheDirPath;
        this.lruCacheMap = new HashMap<>(4);
        this.targetList = new ArrayList<>(20);
    }

    public synchronized LruCache<String, SVGAVideoEntity> getLruCache(String key) {
        LruCache<String, SVGAVideoEntity> cache = lruCacheMap.get(key);
        if (cache == null) {
            cache = new LruCache<>(150);
            lruCacheMap.put(key, cache);
        }
        return cache;
    }

    public void clearCache(String key) {
        lruCacheMap.remove(key);
    }

    /**
     * 获取svga缓存文件路径
     * todo
     *
     * @return
     */
    public String getSVGACacheFilePath() {
        File file = new File(cacheDirPath);
        if (!file.exists() || !file.isDirectory()) {
            boolean ok = file.mkdirs();
            if (!ok) {
                file = TApplication.getInstance().getCacheDir();
            }
        }
        return file.getAbsolutePath();
    }

    /**
     * 解析svga文件
     *
     * @param url
     * @param file
     */
    public synchronized void decodeFile(String url, File file, String cacheName) {
        try {
            InputStream inputStream = new FileInputStream(file);
            mSVGAParser.decodeFromInputStream(inputStream, cacheName, new SVGAParserCompletion(url), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url      下载地址
     * @param cacheDir 缓存文件夹路径
     * @param fileName 文件名称
     */
    public synchronized void downloadFile(String url, File cacheDir, String fileName) {
        int downloadId = PRDownloader.download(url, cacheDir.getAbsolutePath(), fileName)
                .setTag(url).build().start(new FileDownloadListener(url, cacheDir, fileName));
    }

    public void add(Target<SVGAExtView> target) {
        targetList.add(target);
    }

    public List<Target<SVGAExtView>> getTargets(@NonNull String url) {
        List<Target<SVGAExtView>> list = new ArrayList<>();
        Iterator<Target<SVGAExtView>> it = targetList.iterator();
        while (it.hasNext()) {
            Target target = it.next();
            if (!target.isUseful()) {
                it.remove();
            } else if (url.equals(target.getUrl())) {
                list.add(target);
                it.remove();
            }
        }
        return list;
    }
}
