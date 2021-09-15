package com.example.thread_looper_handler_unit3_we;

import android.os.Handler;
import android.os.Looper;

public class WorkerThread extends Thread{
    private Handler handler;

    @Override
    public void run() {
        super.run();
        Looper.prepare();//this will give u message queue
        handler = new Handler(Looper.myLooper());// add the message to the queue
        Looper.loop();// process queue
    }
    public void addTaskToMessageQueue(Runnable task){
        if(handler!= null){
            handler.post(task);
        }
    }

}
