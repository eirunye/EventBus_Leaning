package com.eirunye.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.eirunye.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StickyTestActivity extends AppCompatActivity {

    private final static String TAG = StickyTestActivity.class.getSimpleName();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_test);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    //
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageStickyEvent(MessageEvent event) {

        Log.d(TAG, "onMessageStickyEvent。。。");
        textView.setText(event.message);

        //表示如果粘性事件不为空，则手动删除手动获取和删除粘性事件，上面的testSticky方法无法获取粘性事件的信息了
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        if (stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
            testSticky();
        }
    }

    private void testSticky() {
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        //检查事件是否实际发布
        if (stickyEvent != null) {
            Log.d(TAG, "stickyEvent。。。");
            textView.setText("接收信息：" + stickyEvent.message);
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
