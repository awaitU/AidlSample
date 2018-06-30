package com.awaitu.aidlserver;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.awaitu.aidl.IService;

public class AIDLService extends Service {
	

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return new IService.Stub() {
			@Override
			public String hello(String name) throws RemoteException {
				// TODO Auto-generated method stub
				return "hello"+name;
			}
		};
    }

   
}
