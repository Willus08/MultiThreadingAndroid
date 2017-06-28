package com.example.android.multithreadingandroid;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Android on 6/28/2017.
 */

public class TestAsyc extends AsyncTask<String,Integer, String> {
    TextView textView1;
    ProgressBar progress;

    public TestAsyc(TextView textView, ProgressBar progressBar) {
        this.textView1 = textView;
        this.progress = progressBar;
    }

    @Override
    protected String doInBackground(String... params) {

        for (int i = 0; i <10 ; i++) {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EventBus.getDefault().post(new HelloEvent("hello" + i));
            System.out.println("doInBackground" + Thread.currentThread());
            publishProgress(i);

        }


        return "Done with Task";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView1.setText("Startig Async");
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textView1.setText(String.valueOf(values[0]));
        progress.setProgress(values[0]*10);

    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView1.setText(s);
        progress.setVisibility(View.GONE);
        progress.setProgress(0);
    }
}
