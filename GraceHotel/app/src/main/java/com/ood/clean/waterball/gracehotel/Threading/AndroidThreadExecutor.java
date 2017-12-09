package com.ood.clean.waterball.gracehotel.Threading;


import android.os.Handler;

public class AndroidThreadExecutor implements ThreadExecutor{
    private Handler handler = new Handler();

    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }

    @Override
    public void executeOnMainThread(Runnable runnable) {
        handler.post(runnable);
    }
}
