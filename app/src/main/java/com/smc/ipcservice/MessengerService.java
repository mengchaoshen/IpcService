package com.smc.ipcservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author shenmengchao
 * @version 1.0.0
 * @date 2017/12/25
 * @description
 */

public class MessengerService extends Service {

    private final static int WHAT = 1;

    private Handler mServiceHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT:
                    Bundle clientBundle = (Bundle) msg.obj;
                    String clientStr = (String) clientBundle.get("str");
                    Log.d("clientStr", "client 发来的消息是 ： " + clientStr);
                    Message msgService = Message.obtain();
                    msgService.what = WHAT;
                    Bundle serviceBundle = new Bundle();
                    serviceBundle.putString("str", "我是服务端，我已经收到你的消息了，加油");
                    msgService.obj = serviceBundle;
                    try {
                        msg.replyTo.send(msgService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private Messenger mServiceMessenger = new Messenger(mServiceHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServiceMessenger.getBinder();
    }
}
