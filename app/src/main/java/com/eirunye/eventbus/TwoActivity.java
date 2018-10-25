package com.eirunye.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eirunye.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class TwoActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn_finish;

    private EditText et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        textView = findViewById(R.id.text);
        btn_finish = findViewById(R.id.btn_finish);
        et_message = findViewById(R.id.et_message);

        btn_finish.setOnClickListener(this::onClickView);
    }

    private void onClickView(View view) {
        String message = et_message.getText().toString();
        if ("".equals(message))
            return;
        EventBus.getDefault().post(new MessageEvent(message));

//        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
