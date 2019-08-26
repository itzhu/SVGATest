package me.test.svga.loader;

import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/24.
 */
class LoadResult {

    public static final int CODE_SUCCESS = 100;
    public static final int CODE_ERROR_1001 = -1001;


    private int stateCode;

    private String key;
    private String tag;

    private SVGAVideoEntity svgaVideoEntity;

    public LoadResult(int stateCode, String key, String tag) {
        this.stateCode = stateCode;
        this.key = key;
        this.tag = tag;
    }

    public void setSvgaVideoEntity(SVGAVideoEntity svgaVideoEntity) {
        this.svgaVideoEntity = svgaVideoEntity;
    }

    public SVGAVideoEntity getSvgaVideoEntity() {
        return svgaVideoEntity;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String getKey() {
        return key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS == stateCode;
    }

}
