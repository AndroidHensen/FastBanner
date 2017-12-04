package com.handsome.fastbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handsome.library.adapter.BannerAdapter;
import com.handsome.library.banner.FastBanner;

import java.util.ArrayList;

public class IndicationActivity extends AppCompatActivity {

    private ArrayList<String> url_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indication);

        url_list = new ArrayList<>();
        url_list.add("https://gw.alicdn.com/imgextra/i1/155/TB2LJaYfwLD8KJjSszeXXaGRpXa_!!155-0-luban.jpg_q50.jpg");
        url_list.add("https://img.alicdn.com/imgextra/i1/67/TB2KRpQgNPI8KJjSspfXXcCFXXa_!!67-0-yamato.jpg_q50.jpg");
        url_list.add("https://aecpm.alicdn.com/simba/img/TB14ab1KpXXXXclXFXXSutbFXXX.jpg_q50.jpg");
        url_list.add("https://img.alicdn.com/imgextra/i2/133/TB2pt5zb1LM8KJjSZFqXXa7.FXa_!!133-0-luban.jpg_q50.jpg");

        FastBanner fast_banner2 = (FastBanner) findViewById(R.id.fast_banner);
        fast_banner2.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position) {
                ImageView imageView = new ImageView(IndicationActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(IndicationActivity.this).load(url_list.get(position)).into(imageView);
                return imageView;
            }

            @Override
            public int getCount() {
                return url_list.size();
            }

            @Override
            public void onClick(int position) {
                Toast.makeText(IndicationActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
