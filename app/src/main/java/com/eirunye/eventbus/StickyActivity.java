package com.eirunye.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eirunye.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Author Eirunye
 * Created by on 2018/10/16.
 * Describe
 */
public class StickyActivity extends AppCompatActivity {

    private Button btn_send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(this::onClickView);
        EventBus.getDefault().postSticky(new MessageEvent("发送粘性事件！"));
    }

    private void onClickView(View view) {
        startActivity(new Intent(StickyActivity.this, StickyTestActivity.class));
    }
}
