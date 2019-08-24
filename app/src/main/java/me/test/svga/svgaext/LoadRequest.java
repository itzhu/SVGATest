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
    }

    public void createTag() {
        if (tag == null) {
            SVGAImageView svgaView = svgaImageView.get();
            if (svgaView != null) {
                this.tag = String.valueOf(System.currentTimeMillis());
                svgaView.setTag(tag);
                return true;
            }
        }
        return false;
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
