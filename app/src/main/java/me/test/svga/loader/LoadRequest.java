package me.test.svga.loader;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by itzhu on 2019/8/24.
 */
class LoadRequest {

    private WeakReference<SVGATarget> svgaTarget;
    private URL url;
    /**
     * tag为svgaImageView的hashcode值
     */
    private String key;
    private String tag;

    /**
     * @param url 请求的链接
     */
    public LoadRequest(@NonNull SVGATarget target, @NonNull URL url) {
        this.svgaTarget = new WeakReference<>(target);
        this.url = url;
        this.key = String.valueOf(target.getView().hashCode());
    }

    public boolean createTag() {
        if (tag == null) {
            SVGATarget target = svgaTarget.get();
            if (target != null) {
                this.tag = String.valueOf(System.currentTimeMillis());
                target.setTag(tag);
                return true;
            }
        }
        return false;
    }

    public SVGAParserCompletion createSvgaParserCompletion() {
        return new SVGAParserCompletion(key, tag);
    }

    public URL getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    /**
     * @return maybe null
     */
    public SVGATarget getSvgaTarget() {
        return svgaTarget.get();
    }

}
