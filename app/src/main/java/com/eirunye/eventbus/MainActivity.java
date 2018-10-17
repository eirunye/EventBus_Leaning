package com.eirunye.eventbus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eirunye.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn_skip;

    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        textView = findViewById(R.id.text);
        btn_skip = findViewById(R.id.btn_skip);

        btn_skip.setOnClickListener(this::shipFun);

    }

    private void shipFun(View view) {

        startActivity(new Intent(MainActivity.this, TwoActivity.class));
//        startActivity(new Intent(MainActivity.this, StickyActivity.class));
//        startActivity(new Intent(MainActivity.this, StickyTestActivity.class));
//        startActivity(new Intent(MainActivity.this, PriorityTestActivity.class));
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        textView.setText(event.message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
