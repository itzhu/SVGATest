package me.test.svga.loader;

import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/24.
 * 这个类是不允许直接new的
 */
class SVGAParserCompletion implements SVGAParser.ParseCompletion {

    private String svgaViewKey;
    private String svgaViewTag;

    public SVGAParserCompletion(String svgaViewKey, String svgaViewTag) {
        this.svgaViewKey = svgaViewKey;
        this.svgaViewTag = svgaViewTag;
    }

    @Override
    public void onComplete(SVGAVideoEntity svgaVideoEntity) {
        LoadResult result = new LoadResult(LoadResult.CODE_SUCCESS, svgaViewKey, svgaViewTag);
        result.setSvgaVideoEntity(svgaVideoEntity);
        SVGALoaderManager.getInstance().dealResult(result);
    }

    @Override
    public void onError() {
        LoadResult result = new LoadResult(LoadResult.CODE_ERROR_1001, svgaViewKey, svgaViewTag);
        SVGALoaderManager.getInstance().dealResult(result);
    }
}
