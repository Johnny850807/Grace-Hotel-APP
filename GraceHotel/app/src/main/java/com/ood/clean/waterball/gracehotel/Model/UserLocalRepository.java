package com.ood.clean.waterball.gracehotel.Model;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.datamodel.Permission;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.ItemArranger;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeItemPool;
import com.ood.clean.waterball.gracehotel.android.TreasureAlarmReceiver;

import java.util.Collections;
import java.util.List;

public class UserLocalRepository implements UserRepository{
    private static final String TAG = "UserLocalRepository";
    private static final String SPNAME = "grace";
    private static final String ERROR = "ERROR";
    private static final String USER = "USER";
    private Context context;
    private SharedPreferences sp;
    private Serializer serializer;
    private ItemArranger itemArranger;
    private AlarmManager alarmManager;

    public UserLocalRepository(Context context,
                               Serializer serializer,
                               ItemArranger itemArranger){
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.serializer = serializer;
        this.itemArranger = itemArranger;
        sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean userExists() {
        return !sp.getString(USER, ERROR).equals(ERROR);
    }

    @Override
    public User getUser() {
        String userJson = sp.getString(USER, ERROR);
        Log.d(TAG, "Get user: " + userJson);
        return serializer.deserialize(userJson, User.class);
    }

    @Override
    public User createUser(String roomNumber) {
        String deviceId = getDeviceUID();
        User user = new User(roomNumber, deviceId, "");  //TODO emails
        arrangeTimeItemPoolsWithServiceAndUpdate(user, itemArranger.arrange(3)); //TODO durationDays
        return user;
    }

    @Override
    public void addReward(User user) {
        user.setRewardAmount(user.getRewardAmount() + 1);
        updateUser(user);
    }

    private void arrangeTimeItemPoolsWithServiceAndUpdate(User user, List<TimeItemPool> pools){
        Collections.sort(pools);
        Log.d(TAG, TimeItemPool.asString(pools));
        user.setTimeItemPools(pools);
        updateUser(user);

        for(int i = 0 ; i < pools.size() ; i ++ )
            setTreasureBroadcast(pools.get(i), i);
    }

    /**
     *  notify the user in the time pool the treasure shows up
     *  @param index the index of the pool in the list, the request code of the pendingIntent will reference the index,
     *               used for canceling the pendingIntent further.
     */
    private void setTreasureBroadcast(TimeItemPool pool, int index){
        if(pool.hasTreasure())
        {
            Intent alarmIntent = new Intent(context, TreasureAlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, index, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, pool.getStartTime().getTime(), pendingIntent);
        }
    }

    private String getDeviceUID(){
        //TODO find better solution
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    @Override
    public void addMoney(User user, int money) {
        user.setMoney(user.getMoney() + money);
        updateUser(user);
    }

    private void updateUser(User user){
        String userJson = serializer.serialize(user);
        Log.d(TAG, "Update user: " + userJson);
        sp.edit().putString(USER, userJson).apply();
    }

    @Override
    public void buyPermission(User user, Permission permission) {
        user.addPermission(permission);
        updateUser(user);
    }

    @Override
    public void addFilledQuestionGroupId(User user, int questionGroupId) {
        user.addFilledQuestionGroupId(questionGroupId);
        updateUser(user);
    }
}
