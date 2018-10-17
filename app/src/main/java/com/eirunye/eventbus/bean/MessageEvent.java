package com.eirunye.eventbus.bean;

/**
 * Author Eirunye
 * Created by on 2018/10/16.
 * Describe
 */
public class MessageEvent {

    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
