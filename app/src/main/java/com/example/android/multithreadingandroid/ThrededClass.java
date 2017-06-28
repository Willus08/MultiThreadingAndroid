package com.example.android.multithreadingandroid;

import android.os.Looper;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.logging.Handler;

/**
 * Created by Android on 6/28/2017.
 */

public class ThrededClass implements Runnable {
   TextView text;
    TextView text2;

    public ThrededClass(TextView text, TextView text2) {
        this.text = text;
        this.text2 = text2;
    }


    android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());

    public void run() {

        for ( int i = 0; i < 10 ; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String finaled = "" + i;
            EventBus.getDefault().post(new HelloEvent("hello" + i));
            handler.post(new Runnable() {
                @Override
                public void run() {

                    text.setText(finaled);
                }
            });
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    text2.setText("" + finaled);
                }
            },2000);
            System.out.println("Counter: " +i+ "Thread: "+ Thread.currentThread());
        }
    }
}
