package com.djj.example.wocao;

import android.app.Application;

import org.xutils.x;

/**
 * Created by djj on 2016/11/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
