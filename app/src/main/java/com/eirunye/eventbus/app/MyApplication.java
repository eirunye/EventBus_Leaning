package com.eirunye.eventbus.app;

import android.app.Application;

import com.eirunye.eventbus.BuildConfig;
import com.eirunye.eventbus.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * Author Eirunye
 * Created by on 2018/10/17.
 * Describe
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        //在debug状态下可以使用，抛出异常处理信息
//        EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus();

    }
}
