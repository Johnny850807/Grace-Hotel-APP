package com.ood.clean.waterball.gracehotel.Model;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ood.clean.waterball.gracehotel.Model.datamodel.Permission;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;

public class UserLocalRepository implements UserRepository{
    private static final String SPNAME = "grace";
    private static final String ERROR = "ERROR";
    private static final String USER = "USER";
    private static final String ITEM_ARRANGEMENT = "item arrangement";
    private SharedPreferences sp;
    private Gson gson = new Gson();

    public UserLocalRepository(Context context){
        sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean userExists() {
        return !sp.getString(USER, ERROR).equals(ERROR);
    }

    @Override
    public User getUser() {
        String userJson = sp.getString(USER, ERROR);
        return gson.fromJson(userJson, User.class);
    }

    @Override
    public User createUser(String roomNumber) {
        User user = new User(roomNumber);
        updateUser(user);
        return user;
    }

    @Override
    public void addMoney(User user, int money) {
        user.setMoney(user.getMoney() + money);
        updateUser(user);
    }

    private void updateUser(User user){
        String userJson = gson.toJson(user);
        sp.edit().putString(USER, userJson).apply();
    }

    @Override
    public void buyPermission(User user, Permission permission) {
        user.addPermission(permission);
        updateUser(user);
    }
}
