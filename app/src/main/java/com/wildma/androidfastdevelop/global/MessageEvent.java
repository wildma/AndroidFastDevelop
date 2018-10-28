package com.wildma.androidfastdevelop.global;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/21
 * Desc	        ${EventBus消息事件类}
 */

public class MessageEvent {

    private int    messageType; //消息类型
    private Object object;  //事件的实体类

    /*具体消息类型*/
    public static final int MESSAGE_TYPE_1 = 1;
    public static final int MESSAGE_TYPE_2 = 2;
    //...

    public MessageEvent(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

