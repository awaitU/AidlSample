package com.awaitu.aidlclient;

import com.awaitu.aidl.IService;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MainActivity extends Activity {

    IService RemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            RemoteService = IService.Stub.asInterface(service);

            try {
                String s = RemoteService.hello("finch");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
    }

    /**
     * 在5.0以后不允许使用隐式方式来启动Service
     */
    private void initService() {
        Intent i = new Intent().setComponent(new ComponentName(
                "com.awaitu.aidlserver",
                "com.awaitu.aidlserver.AIDLService"));
        bindService(i, mConnection, Service.BIND_AUTO_CREATE);
    }

    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}
