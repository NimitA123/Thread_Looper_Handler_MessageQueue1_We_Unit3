package com.example.thread_looper_handler_unit3_we;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public  static final String TAG  = MainActivity.class.getName();
    private Button task1;
    private Button task2;
    private WorkerThread workerThread;
    /*All the UI Operation should be done in mainThread WhatEver the Tasks takes a time should be done
     in Background thread Example Calling APi and many More
     and can not perform ui operation in background thread app will be crash after performance ui perform
    like toast and text change */
    /*with the help of runOnUiOperation we can communicate from background thread to mainThread and
    * perform ui operation and show toast message like file is succussfully download after 5 minutes than
    * show text message like file downloaded */
    /*Looper.prepare() alive thread and Looper.loop() gives tasks one time */
    /*Handler is responsible for adding task with the help of message que*/
    /*workThread.looper.quite() ended thread*/
    private Runnable taskOne = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "Task One "+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable taskTwo = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "Task Two "+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workerThread = new WorkerThread();

        workerThread.start();
        task1 = findViewById(R.id.tvData);
        task2 = findViewById(R.id.tvTask2);
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerThread.addTaskToMessageQueue(taskOne);
            }
        });
        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerThread.addTaskToMessageQueue(taskTwo);
            }
        });
    }
}