package jinyuanyuan.bw.com.two;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/*
 *Author:Ahri_Love
 *Date:2019/1/18
 */public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
