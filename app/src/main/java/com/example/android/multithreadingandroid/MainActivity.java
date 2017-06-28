package com.example.android.multithreadingandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.tvUpdate);
        textView2=(TextView) findViewById(R.id.tvUpdate);
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
}
