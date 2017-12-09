package com.ood.clean.waterball.gracehotel.Threading;



public interface ThreadExecutor {
    public void execute(Runnable runnable);
    public void executeOnMainThread(Runnable runnable);
}
