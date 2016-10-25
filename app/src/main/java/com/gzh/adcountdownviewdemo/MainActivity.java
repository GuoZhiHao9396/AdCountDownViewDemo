package com.gzh.adcountdownviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gzh.adcountdownview.CountDownView;

/**
 * 广告倒计时控件
 */
public class MainActivity extends AppCompatActivity {
    CountDownView cdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cdv = (CountDownView) findViewById(R.id.cdv);
        cdv.start();
        cdv.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
            }

            @Override
            public void onFinishCount() {
                Toast.makeText(MainActivity.this, "加载完毕", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, SendActivity.class);
                startActivity(in);
                finish();
            }
        });
        cdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdv.stop();
                Intent in = new Intent(MainActivity.this, SendActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
