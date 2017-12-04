package com.handsome.fastbanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handsome.library.adapter.BannerAdapter;
import com.handsome.library.banner.FastBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TitleAndIndication(View view) {
        startActivity(new Intent(this, TitleAndIndicationActivity.class));
    }

    public void Indication(View view) {
        startActivity(new Intent(this, IndicationActivity.class));
    }
}
