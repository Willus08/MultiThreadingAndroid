package com.example.android.multithreadingandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.tvUpdate);
        textView2=(TextView) findViewById(R.id.tvDelay);
        progressBar = (ProgressBar) findViewById(R.id.pbProgress);
}

    public void TestRunable(View view) {
        ThrededClass thrededClass = new ThrededClass(textView,textView2);
        Thread thread = new Thread(thrededClass);
        thread.start();

    }

    public void TestThread(View view) {
        TestThread testThread = new TestThread(textView, textView2);
        testThread.run();
    }

    public void TestInSyncClass(View view) {
        TestAsyc testAsyc = new TestAsyc(textView,progressBar);
        testAsyc.execute();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode =ThreadMode.MAIN)
    public void messaged(HelloEvent event){
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
