package me.test.svga.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAVideoEntity;

/**
 * Created by itzhu on 2019/8/24.
 */
public class SVGAExtView extends SVGAImageView {

    private boolean startAutoPlay = true;
    private SVGAVideoEntity svgaVideoEntity = null;

    public SVGAExtView(Context context) {
        super(context);
    }

    public SVGAExtView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SVGAExtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (startAutoPlay) {
            start();
        }
        SL.d(getTag() + "  " + "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SL.d(getTag() + "  " + "onDetachedFromWindow");

        //ATTENTION 2019/8/24 18:05 itz @desc: 如果这个view被detache之后，动画就被移除了。
        // 但是这个视图仍然是可能存在的，如果动画停止了，svga就不会显示了。
    }

    public void setVideoEntity(@NonNull SVGAVideoEntity entity) {
        if (svgaVideoEntity == entity) {
            //如果相同
            return;
        }
        setVideoItem(entity);
        svgaVideoEntity = entity;
        if (startAutoPlay) {
            startAnimation();
        }
    }

    public void start() {
        startAutoPlay = true;
        if (svgaVideoEntity != null) {
            startAnimation();
        }
    }

    public void pause() {
        startAutoPlay = false;
        pauseAnimation();
    }

    public void stop() {
        startAutoPlay = false;
        stopAnimation(true);
        svgaVideoEntity = null;
    }

}
