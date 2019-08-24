package me.test.svga.svgaext;

import android.content.Context;
import android.util.AttributeSet;

import com.opensource.svgaplayer.SVGAImageView;

/**
 * Created by itzhu on 2019/8/24.
 */
public class SvgaExtView extends SVGAImageView {

    public SvgaExtView(Context context) {
        super(context);
    }

    public SvgaExtView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SvgaExtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //ATTENTION 2019/8/24 18:05 itz @desc: 如果这个view被detache之后，动画就被移除了。
        // 但是这个视图仍然是可能存在的，如果动画停止了，svga就不会显示了。
    }
}
