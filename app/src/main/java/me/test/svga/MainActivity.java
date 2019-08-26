package me.test.svga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import me.test.svga.loader.SL;
import me.test.svga.loader.SVGAExtView;
import me.test.svga.loader.SVGALoader;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mSVGAListRV;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private LruCache<String, SVGAVideoEntity> mVideoEntityCache;
    private static final int CACHE_MAX_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoEntityCache = new LruCache<>(CACHE_MAX_SIZE);
        mSVGAListRV = findViewById(R.id.rv_svga_list);
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_svga_list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                SVGAExtView svgaExtView = helper.getView(R.id.svga_view);
                SVGALoader.load(item).cache(mVideoEntityCache).into(svgaExtView);
                int position = helper.getLayoutPosition();
                svgaExtView.setTag(position);
                SL.d("position->" + position);
                helper.setText(R.id.tv_text, position + " - " + item.substring(item.length() - 20));
            }
        };
        mSVGAListRV.setLayoutManager(new GridLayoutManager(this, 3));
        mSVGAListRV.setAdapter(mAdapter);

        //loadData();
    }


}
