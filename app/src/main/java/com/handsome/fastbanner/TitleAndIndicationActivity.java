package com.handsome.fastbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handsome.banner.adapter.BannerAdapter;
import com.handsome.banner.banner.FastBanner;

import java.util.ArrayList;
import java.util.List;

public class TitleAndIndicationActivity extends AppCompatActivity {

    private List<String> url_list;
    private List<String> title_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_adn_indication);

        url_list = new ArrayList<>();
        url_list.add("https://img.alicdn.com/imgextra/i4/100/TB2vKF0clLN8KJjSZPhXXc.spXa_!!100-0-yamato.jpg_q50.jpg");
        url_list.add("https://img.alicdn.com/imgextra/i2/65/TB2qMK_b8bM8KJjSZFFXXaynpXa_!!65-0-luban.jpg_q50.jpg");
        url_list.add("https://img.alicdn.com/imgextra/i3/174/TB2EMewf3fH8KJjy1zcXXcTzpXa_!!174-0-luban.jpg_q50.jpg");
        url_list.add("https://gw.alicdn.com/imgextra/TB2FRATfCfD8KJjSszhXXbIJFXa_!!185-0-luban.jpg_q50.jpg");
        title_list = new ArrayList<>();
        title_list.add("会飞会游泳的茶林鸡");
        title_list.add("上海天际线首发");
        title_list.add("星巴克精选");
        title_list.add("二次元集结日");

        FastBanner fast_banner = (FastBanner) findViewById(R.id.fast_banner);
        fast_banner.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position) {
                ImageView imageView = new ImageView(TitleAndIndicationActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(TitleAndIndicationActivity.this).load(url_list.get(position)).into(imageView);
                return imageView;
            }

            @Override
            public int getCount() {
                return url_list.size();
            }

            @Override
            public void onClick(int position) {
                Toast.makeText(TitleAndIndicationActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public String getTitle(int position) {
                return title_list.get(position);
            }

            @Override
            public boolean autoScroll() {
                return true;
            }
        });
    }
}
