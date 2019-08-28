package me.test.svga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import me.test.svga.loader.SL;
import me.test.svga.loader.SVGACacheManager;
import me.test.svga.svgaloader.SVGAExtView;
import me.test.svga.loader.SVGALoader;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mSVGAListRV;
    private BaseQuickAdapter<TextItem, BaseViewHolder> mAdapter;
    private static final int CACHE_MAX_SIZE = 100;
    private static final String CACHE_KEY = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSVGAListRV = findViewById(R.id.rv_svga_list);
        mAdapter = new BaseQuickAdapter<TextItem, BaseViewHolder>(R.layout.item_svga_list) {
            @Override
            protected void convert(BaseViewHolder helper, TextItem item) {
                SVGAExtView svgaExtView = helper.getView(R.id.svga_view);
                SVGALoader.load(item.url).cache(CACHE_KEY).into(svgaExtView);
                int position = helper.getLayoutPosition();
                SL.d("position->" + position);
                helper.setText(R.id.tv_text, position + " - " + item.title);
            }
        };
        mSVGAListRV.setLayoutManager(new GridLayoutManager(this, 3));
        mSVGAListRV.setAdapter(mAdapter);

        //loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SVGACacheManager.getInstance().clearCache(CACHE_KEY);
    }



    class TextItem {
        String url;
        String title;

        public TextItem(String title, String url) {
            this.url = url;
            this.title = title;
        }
    }
}
