package com.smc.ipcservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.smc.aidl.CalculateAidl;

/**
 * @author shenmengchao
 * @version 1.0.0
 * @date 2017/12/25
 * @description
 */

public class CalculateService extends Service {

    public CalculateService() {

    }

    private IBinder mIBinder = new CalculateAidl.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
