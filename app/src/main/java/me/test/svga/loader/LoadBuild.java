package me.test.svga.loader;

import android.support.annotation.IdRes;
import android.util.LruCache;

import com.opensource.svgaplayer.SVGAVideoEntity;

import java.net.URL;

/**
 * Created by itzhu on 2019/8/26.
 */
public class LoadBuild {

    public LoadBuild(String url) {
        this.url = url;
    }

    /**
     * 要加载的Url
     */
    String url;

    /**
     * 缓存SVGAVideoEntity 的cache
     */
    LruCache<String, SVGAVideoEntity> videoEntityLruCache;

    /**
     * 加载失败显示的drawableId
     */
    @IdRes
    int errorDrawableId;

    /**
     * 加载过程中显示的drawableid
     */
    @IdRes
    int loadingDrawableId;

    public LoadBuild cache(LruCache<String, SVGAVideoEntity> videoEntityLruCache) {
        this.videoEntityLruCache = videoEntityLruCache;
        return this;
    }

    public LoadBuild error(int errorDrawableId) {
        this.errorDrawableId = errorDrawableId;
        return this;
    }

    public LoadBuild loading(int loadingDrawableId) {
        this.loadingDrawableId = loadingDrawableId;
        return this;
    }

    public void into(SVGAExtView svgaExtView) {
        into(new SVGATarget<>(svgaExtView));
    }

    public void into(SVGATarget target) {
        try {
            LoadRequest request = new LoadRequest(target, new URL(url));
            SVGALoaderManager.getInstance().addRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
