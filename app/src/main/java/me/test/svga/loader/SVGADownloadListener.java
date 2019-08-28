package me.test.svga.loader;

import com.downloader.Error;
import com.downloader.OnDownloadListener;

import java.io.File;

import me.test.svga.svgaloader.Result;

/**
 * Created by itzhu on 2019/8/27.
 */
public class SVGADownloadListener implements OnDownloadListener {
    private String url;
    private File cacheDir;
    private String fileName;

    public SVGADownloadListener(String url, File cacheDir, String fileName) {
        this.url = url;
        this.cacheDir = cacheDir;
        this.fileName = fileName;
    }

    @Override
    public void onDownloadComplete() {
        SVGACacheManager.getInstance().decodeFile(url, new File(cacheDir, fileName), fileName);
    }

    @Override
    public void onError(Error error) {
        SL.d("error->",error);
        Result result = new Result(Result.CODE_ERROR_1001,url,null);
        SVGALoader.loadFinished(result);
    }
}
