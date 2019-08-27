package me.test.svga.loader;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.LruCache;

import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/27.
 */
public class SVGAUtil {

    private static final String FILE_END_SVGA = ".svga";
    private static final String DEFAULT = "default";

    /**
     * 根据URL创建文件名称
     *
     * @param url
     * @return
     */
    public static String createFileName(@NonNull String url) {
        String svgaName = "";
        try {
            svgaName = "sv_head_file_" + Base64.encodeToString(url.getBytes(), Base64.NO_WRAP);
            if (!svgaName.endsWith(FILE_END_SVGA)) {
                svgaName += FILE_END_SVGA;
            }
        } catch (Exception e) {
            e.printStackTrace();
            svgaName = url.substring(url.lastIndexOf("/")) + DEFAULT + FILE_END_SVGA;
        }
        return svgaName;
    }


    private static SVGAVideoEntity load(LruCache<String, SVGAVideoEntity> lruCache, String url) {
        if (lruCache != null) {
            return lruCache.get(url);
        }
        return null;
    }

}
