package me.test.svga.svgaext;

import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/24.
 */
public class LoadRequestResult {

    public static final int CODE_SUCCESS = 100;
    public static final int CODE_ERROR_1001 = -1001;


    private int stateCode;

    private String svgaViewKey;
    private String svgaViewTag;

    private SVGAVideoEntity svgaVideoEntity;

    public LoadRequestResult(int stateCode, String svgaViewKey) {
        this.stateCode = stateCode;
        this.svgaViewKey = svgaViewKey;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS == stateCode;
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

    public String getSvgaViewKey() {
        return svgaViewKey;
    }

    public String getSvgaViewTag() {
        return svgaViewTag;
    }

    public void setSvgaViewTag(String svgaViewTag) {
        this.svgaViewTag = svgaViewTag;
    }
}
