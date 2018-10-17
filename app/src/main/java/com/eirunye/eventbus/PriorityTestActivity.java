package com.eirunye.eventbus;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eirunye.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PriorityTestActivity extends AppCompatActivity {

    private TextView tv_showUp, tv_showLow;
    private Button btn_send;
    private EditText et_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_test);
        EventBus.getDefault().register(this);

        tv_showUp = findViewById(R.id.tv_showUp);
        tv_showLow = findViewById(R.id.tv_showLow);
        btn_send = findViewById(R.id.btn_send);
        et_priority = findViewById(R.id.et_priority);

        btn_send.setOnClickListener(this::onClickView);
    }

    private void onClickView(View view) {
        String message = et_priority.getText().toString();
        if ("".equals(message)) return;
        EventBus.getDefault().post(new MessageEvent(message));
    }


    @Subscribe(priority = 1000, threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent event) {
        tv_showUp.setText(event.message);
        //取消事件传递，则低级别的事件无法接收到信息必须在threadMode = ThreadMode.POSTING才能生效
        EventBus.getDefault().cancelEventDelivery(event) ;
    }

    @Subscribe(priority = 1, threadMode = ThreadMode.BACKGROUND)
    public void onEventLow(MessageEvent event) {
        //当优先级高的取消事件传递时此处无法获取到数据
        tv_showLow.setText(event.message);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
