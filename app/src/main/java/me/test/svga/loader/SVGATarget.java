package me.test.svga.loader;

import com.opensource.svgaplayer.SVGAVideoEntity;

import java.lang.ref.WeakReference;

/**
 * Created by itzhu on 2019/8/26.
 */
public class SVGATarget<T extends SVGAExtView> {

    private WeakReference<T> viewRef;
    private String tag;

    public SVGATarget(T view) {
        this.viewRef = new WeakReference<>(view);
    }

    void loadFinish(LoadResult result) {
        if (result.isSuccess()) {
            SVGAVideoEntity entity = result.getSvgaVideoEntity();
            T view = viewRef.get();
            if (view != null) {
                view.setVideoEntity(entity);
            }
        }
    }

    public T getView() {
        return viewRef.get();
    }

    public SVGATarget<T> setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getTag() {
        return tag;
    }
}
