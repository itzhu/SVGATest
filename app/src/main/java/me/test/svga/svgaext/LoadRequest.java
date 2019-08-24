package me.test.svga.svgaext;

import android.support.annotation.NonNull;

import com.opensource.svgaplayer.SVGAImageView;

import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by itzhu on 2019/8/24.
 */
public class LoadRequest {

    private WeakReference<SVGAImageView> svgaImageView;
    /**
     * http://file.eebbk.net/server-fileserve/cloudIDN/fileServer/2019/03/22/155255925_4c9c5ef200849b62
     */
    private URL url;

    /**
     * tag为svgaImageView的hashcode值
     */
    private String key;
    private String tag;

    /**
     * @param svgaImageView 会设置tag。请不要为这个imageview设置默认tag
     * @param url           请求的链接
     */
    public LoadRequest(@NonNull SVGAImageView svgaImageView, @NonNull URL url) {
        this.svgaImageView = new WeakReference<>(svgaImageView);
        this.url = url;
        this.key = String.valueOf(svgaImageView.hashCode());
        this.tag = String.valueOf(System.currentTimeMillis());
        svgaImageView.setTag(tag);
    }

    public SVGAParserCompletion createSvgaParserCompletion() {
        return new SVGAParserCompletion(key);
    }

    public URL getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getTag() {
        return tag;
    }

    /**
     * @return maybe null
     */
    public SVGAImageView getSvgaImageView() {
        return svgaImageView.get();
    }

}
