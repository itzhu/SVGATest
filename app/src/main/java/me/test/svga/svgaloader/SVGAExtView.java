package me.test.svga.svgaloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAVideoEntity;

import me.test.svga.loader.SL;

/**
 * Created by itzhu on 2019/8/24.
 * //DESC 2019/8/28 9:44 itz @desc: 只有一帧时，表示这个svga是一个今天图片转成的，这里就只显示第一帧图片。
 * //虽然视觉效果看不出什么差别，界面GPU也没有刷新。但是如果只有一帧的时候也显示动画，仍然会占用CPU。
 * 注意frame只有一帧时，将不会开始动画，如果动画包含声音等信息，建议重写SvgaExtView或者自定义属性。
 */
public class SVGAExtView extends SVGAImageView {

    private boolean startAutoPlay = true;
    private SVGAVideoEntity svgaVideoEntity = null;

    /**
     * 检测图片是否只有一帧。
     */
    private boolean svgaIsOneFrame = false;

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
        SL.d("SVGAVideoEntity:" + entity.getFPS() + "---" + entity.getFrames());
        svgaVideoEntity = entity;
        svgaIsOneFrame = (entity.getFrames() <= 1);
        if (startAutoPlay) {
            start();
        }
    }

    public void start() {
        startAutoPlay = true;
        if (svgaVideoEntity != null) {
            if (svgaIsOneFrame) {
                stepToFrame(0, false);
            } else {
                startAnimation();
            }
            //startAnimation();
        }
    }

    public void clear() {
        startAutoPlay = false;
        stopAnimation(true);
        svgaVideoEntity = null;
    }

}
