package com.handsome.fastbanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
