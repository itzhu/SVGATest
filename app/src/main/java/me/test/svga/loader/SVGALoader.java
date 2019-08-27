package me.test.svga.loader;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.LruCache;

import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.util.List;

import me.test.svga.svgaloader.Result;
import me.test.svga.svgaloader.SVGAExtView;
import me.test.svga.svgaloader.Target;

/**
 * Created by itzhu on 2019/8/26.
 */
public class SVGALoader {

    public static Builder load(String url) {
        return new Builder(url);
    }


    private static synchronized void startLoad(@NonNull Target<SVGAExtView> target) {
        String url = target.getUrl();
        SVGACacheManager.getInstance().add(target);

        //从内存缓存读取数据
        String cacheKey = target.getCacheKey();
        if (!TextUtils.isEmpty(cacheKey)) {
            LruCache<String, SVGAVideoEntity> cache = SVGACacheManager.getInstance().getLruCache(cacheKey);
            SVGAVideoEntity entity = cache.get(url);
            if (entity != null) {
                Result result = new Result(Result.CODE_SUCCESS_CACHE, url, entity);
                loadFinished(result);
                return;
            }
        }

        //从缓存文件夹读取数据
        String fileName = SVGAUtil.createFileName(url);
        String filePath = SVGACacheManager.getInstance().getSVGACacheFilePath();
        File file = new File(filePath, fileName);
        if (file.exists()) {
            SVGACacheManager.getInstance().decodeFile(url, file, fileName);
            return;
        }

        //下载网络数据
        SVGACacheManager.getInstance().downloadFile(url, new File(filePath), fileName);
    }

    public static synchronized void loadFinished(@NonNull Result result) {
        String url = result.getUrl();
        List<Target<SVGAExtView>> targets = SVGACacheManager.getInstance().getTargets(url);
        for (Target<SVGAExtView> target : targets) {
            target.setResult(result);
        }
    }

    public static class Builder {

        private Target<SVGAExtView> target;

        public Builder(String url) {
            target = new Target<>(url);
        }

        public Builder cache(String cacheKey) {
            target.setCacheKey(cacheKey);
            return this;
        }

        public Builder error(int errorDrawableId) {
            target.setErrorDrawableId(errorDrawableId);
            return this;
        }

        public Builder loading(int loadingDrawableId) {
            target.setLoadingDrawableId(loadingDrawableId);
            return this;
        }

        public void into(SVGAExtView svgaExtView) {
            target.bindView(svgaExtView);
            startLoad(target);
        }
    }
}
