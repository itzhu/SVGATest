package me.test.svga.svgaloader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/24.
 */
public class Result {

    public static final int CODE_SUCCESS = 100;
    /**
     * 缓存读取数据成功
     */
    public static final int CODE_SUCCESS_CACHE = 101;

    public static final int CODE_ERROR_1001 = -1001;
    public static final int CODE_ERROR_1002 = -1002;

    private int stateCode;
    private String url;
    private SVGAVideoEntity svgaVideoEntity;

    public Result(int stateCode, @NonNull String url, @Nullable SVGAVideoEntity svgaVideoEntity) {
        this.stateCode = stateCode;
        this.url = url;
        this.svgaVideoEntity = svgaVideoEntity;
    }

    public SVGAVideoEntity getSvgaVideoEntity() {
        return svgaVideoEntity;
    }

    public String getUrl() {
        return url;
    }

    public int getStateCode() {
        return stateCode;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS == stateCode || CODE_SUCCESS_CACHE == stateCode;
    }


    @Override
    public String toString() {
        return "Result{" +
                "stateCode=" + stateCode +
                ", url='" + url + '\'' +
                ", svgaVideoEntity=" + svgaVideoEntity +
                '}';
    }
}
