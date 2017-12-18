package com.ood.clean.waterball.gracehotel.Model;


import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.datamodel.Permission;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.ItemArranger;

public class UserLocalRepository implements UserRepository{
    private static final String TAG = "UserLocalRepository";
    private static final String SPNAME = "grace";
    private static final String ERROR = "ERROR";
    private static final String USER = "USER";
    private Context context;
    private SharedPreferences sp;
    private Serializer serializer;
    private ItemArranger itemArranger;

    public UserLocalRepository(Context context,
                               Serializer serializer,
                               ItemArranger itemArranger){
        this.context = context;
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
        user.setTimeItemPools(itemArranger.arrange(3)); //TODO durationDays
        updateUser(user);
        return user;
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
