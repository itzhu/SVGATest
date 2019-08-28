package me.test.svga.svgaloader;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.opensource.svgaplayer.SVGAVideoEntity;

import java.lang.ref.WeakReference;

import me.test.svga.loader.SL;
import me.test.svga.loader.SVGACacheManager;

/**
 * Created by itzhu on 2019/8/27.
 * target只关注url和view
 */
public class Target<V extends SVGAExtView> {

    public static final int URL_TYPE_ASSET = 1;
    public static final int URL_TYPE_FILE = 2;
    public static final int URL_TYPE_HTTP = 3;

    private String url;
    private WeakReference<V> viewRef;

    /**
     * 缓存SVGAVideoEntity cache 的 key
     */
    String cacheKey;

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

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public void setErrorDrawableId(int errorDrawableId) {
        this.errorDrawableId = errorDrawableId;
    }

    public void setLoadingDrawableId(int loadingDrawableId) {
        this.loadingDrawableId = loadingDrawableId;
    }

    public Target(String url) {
        this.url = url;
    }

    public void bindView(V view) {
        this.viewRef = new WeakReference<>(view);
        createViewTag();
    }

    public String getUrl() {
        return url;
    }

    public Object getTag() {
        V view = getView();
        if (view != null) {
            return view.getTag();
        }
        return null;
    }

    public void createViewTag() {
        setTag(String.valueOf(hashCode()));
    }

    private boolean setTag(Object object) {
        V view = getView();
        if (view != null) {
            view.setTag(object);
            return true;
        }
        return false;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public V getView() {
        return viewRef.get();
    }

    /**
     * 检测这个target是否有用
     *
     * @return
     */
    public boolean isUseful() {
        String tag = String.valueOf(hashCode());
        return tag.equals(getTag());
    }

    public void setResult(@NonNull Result result) {
        SL.d("result:" + result.toString());
        SVGAExtView view = getView();
        if (view == null) {
            return;
        }
        if (result.isSuccess()) {
            SVGAVideoEntity entity = result.getSvgaVideoEntity();
            if (cacheKey != null && url != null && entity != null) {
                SVGACacheManager.getInstance().getLruCache(cacheKey).put(url, entity);
            }
            view.setVideoEntity(entity);
            view.start();
        } else {
            view.clear();
        }
    }
}
