package com.ood.clean.waterball.gracehotel.android;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TreasureAlarmReceiver extends BroadcastReceiver{
    private final static String TAG = "TreasureAlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm receives.");

        context.startService(new Intent(context, TreasureNotificationService.class));
    }

}
