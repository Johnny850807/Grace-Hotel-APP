package com.ood.clean.waterball.gracehotel;

import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;


public class MockThreadExecutor implements ThreadExecutor {
    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }

    @Override
    public void executeOnMainThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
