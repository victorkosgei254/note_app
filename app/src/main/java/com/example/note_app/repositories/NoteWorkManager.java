package com.example.note_app.repositories;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class NoteWorkManager extends Thread{
    private static NoteWorkManager instance;
    Handler handler;
    Looper looper;
    private NoteWorkManager(){}

    public static NoteWorkManager getInstance()
    {
        if (instance == null)
        {
            instance = new NoteWorkManager();
        }
        return instance;
    }

    public void postJob(Runnable job)
    {
        Log.d("WORK MANAGER", "postJob: Job started");
        this.handler.post(job);
    }
    @Override
    public void run() {
        Looper.prepare();
        this.handler = new Handler();
        this.looper = Looper.myLooper();
        Looper.loop();
        super.run();
    }
}
