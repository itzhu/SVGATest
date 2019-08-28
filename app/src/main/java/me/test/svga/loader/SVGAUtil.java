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
    private static final int FILE_NAME_MAXLENGTH = 60;

    /**
     * 根据URL创建文件名称
     * 如：http://xxx.xxx.com/xxx/xxx/xxxName
     * 截取xxxName,然后base64加密,如果加密后字符串长度超过100，截取后100个字符。
     * (注意：文件名称长度是有限制的，避免文件名称过长导致创建文件失败)
     *
     * @param url
     * @return
     */
    public static String createFileName(@NonNull String url) {
        String svgaName = "";
        try {
            //使用最后一个"/"之后的名称，避免base64后文件名称过长
            String name = url.substring(url.lastIndexOf("/"));
            svgaName = Base64.encodeToString(name.getBytes(), Base64.NO_WRAP);
            int length = svgaName.length();
            if (length > FILE_NAME_MAXLENGTH) {
                svgaName = svgaName.substring(length - FILE_NAME_MAXLENGTH);
            }
            if (!svgaName.endsWith(FILE_END_SVGA)) {
                svgaName += FILE_END_SVGA;
            }
            svgaName = "sv_head_file_" + svgaName;
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
