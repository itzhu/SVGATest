package me.test.svga.loader;

import android.support.annotation.NonNull;

import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import me.test.svga.svgaloader.Result;

/**
 * Created by itzhu on 2019/8/24.
 * 这个类是不允许直接new的
 */
class SVGAParserListener implements SVGAParser.ParseCompletion {

    private String url;

    public SVGAParserListener(@NonNull String url) {
        this.url = url;
    }

    @Override
    public void onComplete(SVGAVideoEntity svgaVideoEntity) {
        Result result = new Result(Result.CODE_SUCCESS, url, svgaVideoEntity);
        SVGALoader.loadFinished(result);
    }

    @Override
    public void onError() {
        Result result = new Result(Result.CODE_ERROR_1002, url, null);
        SVGALoader.loadFinished(result);
    }
}
